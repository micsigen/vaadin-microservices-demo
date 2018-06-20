#!/bin/sh
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' $1)" != "200" ]]; do sleep 5; done
java -Xmx100m -Xms100m -jar ./target/admin-application-0.0.1-SNAPSHOT.jar