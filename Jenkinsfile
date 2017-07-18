#!groovy

properties([
    parameters([
        string(defaultValue: '', description: 'REQUIRED', name: 'TESTOBJECT_API_KEY'),
        string(defaultValue: '', description: '', name: 'TESTOBJECT_TEST_NAME'),
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
        choice(choices: 'https://appium.staging.testobject.org/wd/hub\nhttps://appium.dev.testobject.org/wd/hub\nhttps://us1.appium.testobject.com/wd/hub\nhttps://eu1.appium.testobject.com/wd/hub', description: 'REQUIRED', name: 'APPIUM_URL')
        choice(choices: 'RemoteWebDriver\nIOSDriver\nAndroidDriver', description: 'REQUIRED', name: 'DRIVER')
    ])
])

TestRunner {
    steps = {
        sh "./gradlew test -i"
        archiveArtifacts "screenshot.png"
    }
}