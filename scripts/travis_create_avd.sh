#!/usr/bin/env bash

# Creates and starts an android emulator.

echo no | android create avd --force -n test -t android-22 --abi armeabi-v7a
emulator -avd test -no-audio -no-skin -netfast -no-window &

exit 0