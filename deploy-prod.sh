#!/usr/bin/env bash
openssl aes-256-cbc -d -in codesigning.asc.enc -out codesigning.asc -k $ENCKEY
gpg --fast-import cd/codesigning.asc
mvn deploy -P sign,build-extras --settings settings.xml