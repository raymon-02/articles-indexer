#!/bin/sh

sleep ${SLEEP_TIMEOUT}

/usr/bin/java -jar docs-indexer.jar --spring.profiles.active=docker
