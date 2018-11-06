#!/usr/bin/env bash

#/ We use the --settings-file switch to ensure we are building the modules in isolated scopes

# create test reports
./gradlew jacocoTestReport
# run unit tests
./gradlew --settings-file settings_only_providers.gradle clean :ZendeskProviders:assembleDebug :ZendeskProviders:lintDebug :ZendeskProviders:testDebug :ZendeskProviders:debugJavadoc
./gradlew --settings-file settings_only_sdk.gradle :ZendeskSDK:assembleDebug :ZendeskSDK:lintDebug :ZendeskSDK:testDebug :ZendeskSDK:debugJavadoc