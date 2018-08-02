#!/bin/bash

TERM=dumb ./gradlew --info \
    -PsonatypeUsername="${SONATYPE_USER}" \
    -PsonatypePassword="${SONATYPE_PASSWORD}" \
    uploadArchives