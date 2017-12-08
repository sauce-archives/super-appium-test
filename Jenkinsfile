#!groovy
@Library('test-runner')

TestRunner {
    steps = {
        sh "./gradlew clean test -i"
        archiveArtifacts "screenshot.png"
    }
    dockerImage = "java:8"
}
