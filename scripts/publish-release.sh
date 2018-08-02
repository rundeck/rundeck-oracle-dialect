#!/bin/bash

TERM=dumb ./gradlew --info -PdryRun=false \
    -PbintrayUser="${BINTRAY_USER}" \
    -PbintrayApiKey="${BINTRAY_API_KEY}" \
    -PossUserToken="${OSS_USER_TOKEN}" \
    -PossUserPassword="${OSS_USER_PASSWORD}" \
    bintrayUpload