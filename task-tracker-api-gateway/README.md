#### Docker ####

Build image:

* `docker build -t task-tracker-api-gateway .`

Create Container

* `docker run -d -p 8002:9000 -h task-tracker-api-gateway --name task-tracker-api-gateway task-tracker-api-gateway`

Attack to network

* `docker network create myNetwork`
* `docker network connect myNetwork task-tracker-api-gateway`

Delete Continer

* `docker container rm -f task-tracker-api-gateway`

Delete image

* `docker rmi -f task-tracker-api-gateway`