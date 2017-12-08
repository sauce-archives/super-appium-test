#!groovy

// for more information about TestRunner please visit https://github.com/saucelabs/pipeline-test-runner

@Library('test-runner') _

TestRunner {
    steps = {
        sh "./gradlew clean test -i"
        archiveArtifacts "screenshot.png"
    }
    dockerImage = "java:8"
    collectJunitReport = COLLECT_JUNIT_ENABLED.is(true)
    junitReportPath = "/TEST-.xml"
}
