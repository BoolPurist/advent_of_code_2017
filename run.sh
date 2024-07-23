#!/usr/bin/env bash
set -e
mvn compile
mvn exec:java -Dexec.mainClass=Program -Dexec.args="$*"