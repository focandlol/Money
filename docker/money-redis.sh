docker run --name money-redis \
             -p 6379:6379 \
             --network docker_money \
             -d redis:latest
