#!/bin/sh -l

pwd

find .

sh -c "./gradlew check --no-daemon"
