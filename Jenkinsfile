pipeline {
    agent any
    stages {
        stage("Test") {
            steps {
                sh "./mvnw clean test"
            }
        }
        stage("Deploy") {
            steps {
                sh "./scripts/deploy.sh"
            }
        }
    }
}