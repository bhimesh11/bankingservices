# build-images.ps1
# Builds Docker images for all microservices using Jib, and loads them
# into the local Docker daemon so docker-compose can pick them up.
#
# Run this from: D:\java_projects\microservices
#
# Usage:
#   .\build-images.ps1

# List every service folder that has a pom.xml with the Jib plugin.
# Add/remove names here as your project structure changes.
$services = @(
    "accounts",
    "loans",
    "cards",
    "configserver",
    "EurekaServer",
    "gatewayserver"
)

$root = Get-Location
$failed = @()

foreach ($svc in $services) {
    $path = Join-Path $root $svc

    if (-not (Test-Path $path)) {
        Write-Host "SKIP: '$svc' folder not found at $path" -ForegroundColor Yellow
        continue
    }

    Write-Host "`n=== Building image for '$svc' ===" -ForegroundColor Cyan
    Push-Location $path

    mvn compile jib:dockerBuild

    if ($LASTEXITCODE -ne 0) {
        Write-Host "FAILED: $svc" -ForegroundColor Red
        $failed += $svc
    } else {
        Write-Host "OK: $svc image built" -ForegroundColor Green
    }

    Pop-Location
}

Write-Host "`n=== Build summary ===" -ForegroundColor Cyan
if ($failed.Count -eq 0) {
    Write-Host "All images built successfully." -ForegroundColor Green
} else {
    Write-Host "Failed services: $($failed -join ', ')" -ForegroundColor Red
}

Write-Host "`nCurrent bhimesh1998 images:" -ForegroundColor Cyan
docker images | Select-String "bhimesh1998"