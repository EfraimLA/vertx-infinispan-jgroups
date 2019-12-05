pipeline {
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