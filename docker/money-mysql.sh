docker run -d \
--name money-mysql \
-e MYSQL_ROOT_PASSWORD="money" \
-e MYSQL_USER="money" \
-e MYSQL_PASSWORD="money" \
-e MYSQL_DATABASE="money" \
-p 3306:3306 \
--network docker_money mysql:latest