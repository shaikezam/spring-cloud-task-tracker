#### Docker ####

Run docker compose:

* `docker-compose up`

Stop All Containers

* `docker stop $(docker ps -aq)`

Delete All Containers

* `docker rm $(docker ps -aq)`

Delete All Images

* `docker rmi $(docker images -q)`

Run the app

* `http://localhost:8000`
