pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'monapp:school-management'
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_REPO = 'hanaemouhib'
        DOCKER_TAG = 'latest'
        DOCKER_IMAGE_NAME = "$DOCKER_REGISTRY/$DOCKER_REPO/$DOCKER_IMAGE:$DOCKER_TAG"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    echo "Building the project"
                    sh 'mvn clean install'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    echo "Running unit tests"
                    sh 'mvn test'
                }
            }
        }

        stage('Create Docker Image') {
            steps {
                script {
                    echo "Building Docker image"
                    sh "docker build -t $DOCKER_IMAGE_NAME ."
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    echo "Pushing Docker image to DockerHub"
                    withCredentials([usernamePassword(credentialsId: 'DockerHub-Credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh "docker push $DOCKER_IMAGE_NAME"
                    }
                }
            }
        }

        stage('Deploy to Remote Server') {
            steps {
                script {
                    echo "Deploying to remote server"
                    sshagent(credentials: ['3f0a1a7f-fd85-4c52-95a1-957a6ac9d7bc']) {
                        // Pull Docker image directly on the remote server
                        sh "ssh hanaessh@localhost 'docker pull $DOCKER_IMAGE_NAME'"

                        // Restart or create a new container
                        sh 'ssh hanaessh@localhost "docker stop myapp-container || true && docker rm myapp-container || true && docker run -d --name myapp-container $DOCKER_IMAGE_NAME"'
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs!"
        }
    }
}
