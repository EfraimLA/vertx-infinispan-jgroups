node {
    stage("Test") {
        withMaven(){
            sh "mvn clean test"
        }
    }
    stage("Deploy") {
        withMaven(){
            sh "./scripts/deploy.sh"
        }
    }
}