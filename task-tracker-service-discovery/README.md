#### Docker ####

Build image:

* `docker build -t task-tracker-service-discovery .`

Create Container

* `docker run -d -p 4001:9001 -h task-tracker-service-discovery --name task-tracker-service-discovery task-tracker-service-discovery`

Attack to network

* `docker network create myNetwork`
* `docker network connect myNetwork task-tracker-service-discovery`

Delete Continer

* `docker container rm -f task-tracker-service-discovery`

Delete image

* `docker rmi -f task-tracker-service-discovery`