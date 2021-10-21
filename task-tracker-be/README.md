#### Docker ####

Build image:

* `docker build -t task-tracker-app .`

Create Container

* `docker run -d --name task-tracker-app task-tracker-app`

Delete Continer

* `docker container rm -f task-tracker-app`

Delete image

* `docker rmi task-tracker-app`