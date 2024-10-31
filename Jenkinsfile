pipeline {

    agent any

    tools {
        maven 'maven-3.9.9'
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
        SONAR_PROJECT_KEY = 'Demo_ci_cd'
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner'
    }
    stages {
        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }
        stage('Test Coverage') {
                   steps {
                       sh 'mvn test'
                       // Optionally, you can run JaCoCo report generation
                       sh 'mvn jacoco:report'
                   }
               }

        stage('Scan Quality Code') {
                   steps {
                       withCredentials([string(credentialsId: 'demo-ci-cd-token', variable: 'SONAR_TOKEN')]) {
                           withSonarQubeEnv('SonarQubeServer') {
                               sh """
                                   ${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                                   -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                   -Dsonar.sources=. \
                                   -Dsonar.host.url=http://localhost:9001 \
                                   -Dsonar.login=${SONAR_TOKEN} \
                                   -Dsonar.jacoco.reportPath=target/jacoco.exec \
                                   -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                               """
                           }
                       }
                   }
                }
//         stage('Packaging/Pushing imagae') {
//
//             steps {
//
//                 withDockerRegistry(credentialsId: 'docker-hub', url: 'https://index.docker.io/v1/') {
//                                     sh 'docker build -t khaitqvnist/springboot .'
//                                     sh 'docker push khaitqvnist/springboot'
//                                 }
//
//             }
//         }
//
//         stage('Deploy MySQL to DEV') {
//             steps {
//                 echo 'Deploying and cleaning'
//                 sh 'docker image pull mysql:8.0'
//                 sh 'docker network create dev || echo "this network exists"'
//                 sh 'docker container stop khaitq-mysql || echo "this container does not exist" '
//                 sh 'echo y | docker container prune '
//                 sh 'docker volume rm khaitq-mysql-data || echo "no volume"'
//
//                 sh "docker run --name khaitq-mysql --rm --network dev -v khaitq-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
//                 sh 'sleep 20'
//                 sh "docker exec -i khaitq-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
//             }
//         }
//
//         stage('Deploy Spring Boot to DEV') {
//             steps {
//                 echo 'Deploying and cleaning'
//                 sh 'docker image pull khaitqvnist/springboot'
//                 sh 'docker container stop khaitq-springboot || echo "this container does not exist" '
//                 sh 'docker network create dev || echo "this network exists"'
//                 sh 'echo y | docker container prune '
//
//                 sh 'docker container run -d --rm --name khaitq-springboot -p 8081:8080 --network dev khaitqvnist/springboot'
//             }
//         }

    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}