#### Docker ####

Build image:

* `docker image build -t shaikezam/task-tracker:latest .`

Push

* `docker image push shaikezam/task-tracker:latest`

Run

* `docker container run -d --name hello-world -p 8093:8090 shaikezam/task-tracker:latest`

Delete

* `docker container rm -f hello-world`

#### Kubernetes ####

* [Install Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)

* [Installing Kubernetes with Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/#starting-a-cluster)

* Run minikube: `minikube start --vm-driver=none`

Create

* `kubectl apply -f deployment.yaml`

Test

* `kubectl get pods --selector="run=load-balancer-example" --output=wide` and run the IP

To connect to the pod

* `kubectl exec -it <POD_NAME> /bin/sh`

Delete

* `minikube delete`