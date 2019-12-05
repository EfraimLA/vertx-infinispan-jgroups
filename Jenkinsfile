node("launchpad-maven") {
    checkout scm
    stage("Test") {
        sh "mvn clean test"
    }
    stage("Build") {
        sh "mvn clean fabric8:deploy"
    }
    stage("Deploy")
}