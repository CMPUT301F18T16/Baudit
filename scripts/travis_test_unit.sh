#!/usr/bin/env bash

# run unit tests
./gradlew clean assemble jacocoTestReport lint test
