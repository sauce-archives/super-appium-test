#!groovy

properties([
    parameters([
        string(defaultValue: '', description: 'REQUIRED', name: 'TESTOBJECT_API_KEY'),
        choice(choices: 'https://appium.staging.testobject.org/wd/hub\nhttps://appium.dev.testobject.org/wd/hub\nhttps://us1.appium.testobject.com/wd/hub\nhttps://eu1.appium.testobject.com/wd/hub', description: 'REQUIRED', name: 'APPIUM_URL'),
        choice(choices: 'RemoteWebDriver\nIOSDriver\nAndroidDriver', description: 'REQUIRED', name: 'DRIVER'),
        string(defaultValue: 'Super Test!', description: '', name: 'TESTOBJECT_TEST_NAME'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_DEVICE'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_PLATFORM_NAME'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_PLATFORM_VERSION'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_PHONE_ONLY'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_POOL_ID'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_APPIUM_VERSION'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_APP_ID'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_SESSION_CREATION_RETRY'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_SESSION_CREATION_TIMEOUT'),
        booleanParam(defaultValue: false, description: '', name: 'TESTOBJECT_PHONE_ONLY'),
        booleanParam(defaultValue: false, description: '', name: 'TESTOBJECT_TABLET_ONLY'),
        booleanParam(defaultValue: false, description: '', name: 'TESTOBJECT_CACHE_DEVICE'),
        booleanParam(defaultValue: false, description: 'Notify Slack on failure', name: 'FAILURE_NOTIFICATION_ENABLED'),
        booleanParam(defaultValue: false, description: 'Notify Slack on success', name: 'SUCCESS_NOTIFICATION_ENABLED'),
        booleanParam(defaultValue: true, description: 'Collect JUnit XML report', name: 'COLLECT_JUNIT_ENABLED')
    ])
])

TestRunner {
    steps = {
        sh "./gradlew test -i"
        archiveArtifacts "screenshot.png"
    }
    dockerImage = "java:8"
}