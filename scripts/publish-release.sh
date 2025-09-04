#!/bin/bash

# Note: Bintray support has been removed. Release publishing now uses Maven Central.
TERM=dumb ./gradlew --info \
    -PsonatypeUsername="${SONATYPE_USER}" \
    -PsonatypePassword="${SONATYPE_PASSWORD}" \
    uploadArchives
