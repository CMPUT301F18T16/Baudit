#!/usr/bin/env bash

# Creates and starts an android emulator.

echo no | android create avd --force -n test -t android-28 --abi google_apis/armeabi-v7a
emulator -avd test -no-audio -no-skin -netfast -no-window &

exit 0