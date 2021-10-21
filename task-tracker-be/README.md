#### Docker ####

Build image:

* `docker build -t task-tracker-app .`

Create Container

* `docker run -d -h task-tracker-app --name task-tracker-app task-tracker-app`

Attack to network

* `docker network create myNetwork`
* `docker network connect myNetwork task-tracker-app`

Delete Continer

* `docker container rm -f task-tracker-app`

Delete image

* `docker rmi task-tracker-app`