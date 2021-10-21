#### Docker ####

Build image:

* `docker build -t task-tracker-gateway .`

Create Container

* `docker run -d --name task-tracker-gateway task-tracker-gateway`

Delete Continer

* `docker container rm -f task-tracker-gateway`

Delete image

* `docker rmi task-tracker-gateway`