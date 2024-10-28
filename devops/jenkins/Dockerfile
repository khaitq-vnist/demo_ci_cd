FROM jenkins/jenkins:2.462.3-lts-jdk17

# Switch to root to install system packages
USER root

# Update the package list and install necessary dependencies
RUN apt-get update && apt-get install -y apt-transport-https \
    ca-certificates curl gnupg2 \
    lsb-release software-properties-common

# Add Docker's official GPG key
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Set up the Docker stable repository
RUN echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Prevent any potential issue with missing architectures
RUN dpkg --add-architecture amd64

# Update package list again and install Docker CLI
RUN apt-get update && apt-get install -y docker-ce-cli

# Switch back to the Jenkins user
USER jenkins
