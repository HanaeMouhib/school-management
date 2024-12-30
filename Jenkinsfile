pipeline {
    agent any

    environment {
        DOCKER_REPO = 'hanaemouhib'
        DOCKER_IMAGE = 'school-management'
        DOCKER_TAG = 'latest'
        DOCKER_IMAGE_NAME = "${DOCKER_REPO}/${DOCKER_IMAGE}:${DOCKER_TAG}"
        DOCKERHUB_CREDENTIALS = credentials('DockerHub-Credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                   
                    sh 'git config --global http.postBuffer 524288000'

                    
                    git branch: 'master',
                        url: 'https://github.com/HanaeMouhib/school-management.git',
                        credentialsId: 'github-token',
                        shallow: true 
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo "Building the project"
                    sh 'mvn clean install -DskipTests'
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
                    sh "docker build -t ${DOCKER_IMAGE_NAME} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    echo "Pushing Docker image to DockerHub"
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    sh "docker push ${DOCKER_IMAGE_NAME}"
                    sh "docker logout"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "Deploying to remote server"
                    sshagent(['3f0a1a7f-fd85-4c52-95a1-957a6ac9d7bc']) {
                        sh """
                            ssh -o StrictHostKeyChecking=no hanaessh@localhost '
                                echo "${DOCKERHUB_CREDENTIALS_PSW}" | docker login -u "${DOCKERHUB_CREDENTIALS_USR}" --password-stdin
                                docker pull ${DOCKER_IMAGE_NAME}
                                docker stop school-management || true
                                docker rm school-management || true
                                docker run -d --name school-management -p 9090:8080 ${DOCKER_IMAGE_NAME}
                                docker logout'
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Cleaning up..."
            sh 'docker logout'
        }
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs!"
        }
    }
}
