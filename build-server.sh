#!/bin/sh

set -o pipefail

FILE=.
IMAGE=micsigen/vaadin-microservice-build
VERSION=1.0-SNAPSHOT

docker build -t ${IMAGE}:${VERSION} $FILE | tee build.log || exit 1
ID=$(tail -1 build.log | awk '{print $3;}')
