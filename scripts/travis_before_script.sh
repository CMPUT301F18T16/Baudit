#!/usr/bin/env bash

set -e

# Starting emulators is very costly. We should only start them if we're building a matrix
# component which requires one. We start the travis_before_script.sh in the background because
# we can get a small performance improvement by continuing the build, and only blocking and
# waiting for the emulator when we absolutely need it.

if [ "$COMPONENT" == "instrumentation" ]; then
    echo "Starting AVD for component $COMPONENT"
    ./scripts/travis_create_avd.sh &
fi

exit 0