#### Docker ####

Build image:

* `docker build -t task-tracker-ui .`

Create Container

* `docker run -d -p 8000:4000 --name task-tracker-ui task-tracker-ui`

Delete Continer

* `docker container rm -f task-tracker-ui`

Delete image

* `docker rmi task-tracker-ui`