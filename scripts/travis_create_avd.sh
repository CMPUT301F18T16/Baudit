#!/usr/bin/env bash

# Creates and starts an emulator.

# the android-16 emulator is very quick for some reason
android-update-sdk --components=sys-img-$ANDROID_ABI-android-$ANDROID_EMULATOR_LEVEL --accept-licenses='android-sdk-license-[0-9a-f]{8}'
echo "no" | android create avd --force -n test -t "android-"$ANDROID_EMULATOR_LEVEL --abi $ANDROID_ABI --skin QVGA
emulator -avd test -no-audio -no-skin -netfast -no-window &

exit 0