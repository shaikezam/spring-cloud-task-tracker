#### Docker ####

Build image:

* `docker build -t task-tracker-db .`

Create Container

* `docker run -d -p 3306:3306 --name task-tracker-db task-tracker-db`

Attack to network

* `docker network create myNetwork`
* `docker network connect myNetwork task-tracker-db`

Delete Continer

* `docker container rm -f task-tracker-db`

Delete image

* `docker rmi task-tracker-db`