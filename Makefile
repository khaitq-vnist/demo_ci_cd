# Variables
IMAGE_NAME=jenkins-new
VERSION=v1
CONTAINER_NAME=jenkins
NETWORK=jenkins
JENKINS_PORT=8080
AGENT_PORT=50000
JENKINS_VOLUME=jenkins_home
CERTS_VOLUME=docker-certs-jk
DOCKER_HOST=tcp://docker:2376
DOCKER_CERT_PATH=/certs/client
DOCKER_TLS_VERIFY=1
DOCKERFILE_PATH=devops/jenkins/Dockerfile
CONTEXT=.

# Build the Docker image
build:
	sudo docker build -t $(IMAGE_NAME):$(VERSION) -f $(DOCKERFILE_PATH) $(CONTEXT)

# Run the Jenkins container
run:
	sudo docker run --name $(CONTAINER_NAME) -d \
	  --network $(NETWORK) \
	  --env DOCKER_HOST=$(DOCKER_HOST) \
	  --env DOCKER_CERT_PATH=$(DOCKER_CERT_PATH) \
	  --env DOCKER_TLS_VERIFY=$(DOCKER_TLS_VERIFY) \
	  -p $(JENKINS_PORT):8080 -p $(AGENT_PORT):50000 \
	  -v $(JENKINS_VOLUME):/var/jenkins_home \
	  -v $(CERTS_VOLUME):/certs/client:ro \
	  $(IMAGE_NAME):$(VERSION)

# Clean up the container
clean:
	sudo docker stop $(CONTAINER_NAME) || true
	sudo docker rm $(CONTAINER_NAME) || true

# Remove the Docker image
remove-image:
	sudo docker rmi $(IMAGE_NAME):$(VERSION)

.PHONY: build run clean remove-image
