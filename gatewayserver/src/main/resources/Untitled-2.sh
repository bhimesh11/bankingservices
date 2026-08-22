docker run -p 3306:3306 --name localaccountsDB -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql



docker run -p 3307:3306 --name localloansDB -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansDb -d mysql

docker run -p 3308:3306 --name localcardsDB -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsDb -d mysql
