pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
                // Define Docker Hub credentials ID
                DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
                // Define Docker Hub repository name
                DOCKERHUB_REPO = 'olli3290818/shopping_cart'
                // Define Docker image tag
                DOCKER_IMAGE_TAG = 'latest_v1'
            }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kimiollie/ShoppingCart.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -Pjenkins'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Pjenkins'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report -Pjenkins'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
        steps {
            script {
                docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}
