FROM jenkins/jenkins:lts

USER root

# Install Docker
RUN apt-get update && \
    apt-get install -y \
    docker.io && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set the Jenkins user to be able to run Docker commands
RUN usermod -aG docker jenkins

USER jenkins
