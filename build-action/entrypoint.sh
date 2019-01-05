#!/bin/sh -l

cp -r /github/workspace/* .
./gradlew check --no-daemon
