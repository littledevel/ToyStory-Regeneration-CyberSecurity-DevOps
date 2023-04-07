pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/littledevel/ToyStory-Regeneration-CyberSecurity-DevOps.git'
            }
        stage('Validate') {
            steps {
                sh "mvn clean validate"
            }
        }
        stage('Build'){
            sh "mvn compile"
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
                sh "docker tag toystory-$BUILD_NUMBER kmakedos/toystory-$BUILD_NUMBER"
                sh "docker push toystory-$BUILD_NUMBER"
            }
        }
 
    }
}


pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
          stage('Validate') {
            steps {
                git 'https://github.com/littledevel/ToyStory-Regeneration-CyberSecurity-DevOps.git'

                sh "mvn clean validate"
            }

        }
        stage('Build') {
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
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}

