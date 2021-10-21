#### Docker ####

Build image:

* `docker build -t task-tracker-service-discovery .`

Create Container

* `docker run -d -p 4001:9001 --name task-tracker-service-discovery task-tracker-service-discovery`

Delete Continer

* `docker container rm -f task-tracker-service-discovery`

Delete image

* `docker rmi task-tracker-service-discovery`