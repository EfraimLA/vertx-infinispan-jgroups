pipeline {
    stages {
        stage("Test") {
            steps {
                sh "mvn clean test"
            }
        }
        stage("Deploy") {
            steps {
                sh "./scripts/deploy.sh"
            }
        }
    }
}