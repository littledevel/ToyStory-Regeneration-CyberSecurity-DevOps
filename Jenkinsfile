pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    environment {
        DOCKERHUB_CREDS = credentials('dockerhub')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/littledevel/ToyStory-Regeneration-CyberSecurity-DevOps.git'
            }
        }
        stage('Validate') {
            steps {
                sh "mvn clean validate"
            }
        }
        stage('Build'){
            steps {
                sh "mvn compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Package') {
            steps {
                sh "mvn package"
            }
        }
        stage('Publish'){
            steps{
                sh "mvn verify"
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Docker Image'){
            steps{
                sh "docker build -t toystory-$BUILD_NUMBER ."
            }
        }
        stage('Docker Publish'){
            steps{
                sh  "echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin"
                sh "docker tag toystory-$BUILD_NUMBER brokenleg/toystory-$BUILD_NUMBER"
                sh "docker push toystory-$BUILD_NUMBER"
            }
        }
    }
}