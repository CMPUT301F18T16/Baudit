#!/usr/bin/env bash

# run unit tests
./gradlew clean build jacocoTestReport assembleDebug lintDebug testDebug test
