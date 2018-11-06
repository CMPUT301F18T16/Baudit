#!/usr/bin/env bash

set -e

if [ "$COMPONENT" == "unit" ]; then
    ./scripts/travis_test_unit.sh
elif [ "$COMPONENT" == "instrumentation" ]; then
    ./scripts/travis_test_instrumentation.sh
else
    echo "That module doesn't exist, now does it? ;)"
    exit 1
fi