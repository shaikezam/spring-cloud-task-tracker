#### Docker ####

Build image:

* `docker build -t task-tracker-application .`

Create Container

* `docker run -d -h task-tracker-application --name task-tracker-application task-tracker-application`

Attack to network

* `docker network create myNetwork`
* `docker network connect myNetwork task-tracker-application`

Delete Continer

* `docker container rm -f task-tracker-application`

Delete image

* `docker rmi task-tracker-application`