#!/bin/sh -l

pwd

find .

sh -c "gradle check --stacktrace --debug --scan --no-daemon"
