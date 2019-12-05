node {
    checkout scm
    stage("Test") {
        sh "mvn clean test"
    }
    stage("Build") {
        sh "./scripts/deploy.sh"
    }
    stage("Deploy")
}