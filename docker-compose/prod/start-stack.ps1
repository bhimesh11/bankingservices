# start-stack.ps1
# Brings up the microservices stack in dependency order, waiting for
# each stage's containers to report healthy before moving to the next.
#
# Run this from: D:\java_projects\microservices\docker-compose\prod
#
# Usage:
#   .\start-stack.ps1

$stages = @(
    @{ Name = "Databases";              Services = @("accountsdb","loansdb","cardsdb") },
    @{ Name = "Config Server";           Services = @("configserver") },
    @{ Name = "Eureka Server";           Services = @("eurekaserver") },
    @{ Name = "Business Services";       Services = @("accounts","loans","cards") },
    @{ Name = "Business Services (2nd)"; Services = @("accounts2","loans2","cards2") },
    @{ Name = "Redis";                   Services = @("redis") },
    @{ Name = "Gateway Server";          Services = @("gatewayserver") },
    @{ Name = "Observability - Minio";   Services = @("minio") },
    @{ Name = "Observability - Loki";    Services = @("read","write") },
    @{ Name = "Observability - Backend/Gateway"; Services = @("backend","gateway") },
    @{ Name = "Observability - Metrics"; Services = @("prometheus","grafana","alloy") }
)

function Wait-Healthy {
    param([string[]]$Services, [int]$TimeoutSeconds = 120)

    $elapsed = 0
    $interval = 5

    while ($elapsed -lt $TimeoutSeconds) {
        $allHealthy = $true

        foreach ($svc in $Services) {
            $status = docker compose ps --format json $svc 2>$null | ConvertFrom-Json

            if (-not $status) {
                $allHealthy = $false
                continue
            }

            # Services without a healthcheck report empty Health; treat "running" as good enough for those.
            $health = $status.Health
            $state  = $status.State

            if ($health -and $health -ne "healthy") {
                $allHealthy = $false
            } elseif (-not $health -and $state -ne "running") {
                $allHealthy = $false
            }
        }

        if ($allHealthy) {
            Write-Host "  -> healthy" -ForegroundColor Green
            return $true
        }

        Start-Sleep -Seconds $interval
        $elapsed += $interval
        Write-Host "  ...waiting ($elapsed s)" -ForegroundColor DarkGray
    }

    Write-Host "  -> TIMEOUT waiting for: $($Services -join ', ')" -ForegroundColor Red
    return $false
}

foreach ($stage in $stages) {
    Write-Host "`n=== Stage: $($stage.Name) ===" -ForegroundColor Cyan
    Write-Host "Starting: $($stage.Services -join ', ')"

    docker compose up -d $stage.Services

    if ($LASTEXITCODE -ne 0) {
        Write-Host "Failed to start stage '$($stage.Name)'. Stopping." -ForegroundColor Red
        exit 1
    }

    $ok = Wait-Healthy -Services $stage.Services

    if (-not $ok) {
        Write-Host "`nStage '$($stage.Name)' did not become healthy in time." -ForegroundColor Red
        Write-Host "Check logs with: docker compose logs <service>" -ForegroundColor Yellow
        $continue = Read-Host "Continue to next stage anyway? (y/N)"
        if ($continue -ne "y") {
            exit 1
        }
    }
}

Write-Host "`n=== All stages complete ===" -ForegroundColor Cyan
docker compose ps