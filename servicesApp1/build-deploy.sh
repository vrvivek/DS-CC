#!/bin/sh
#deploy="false"
deploy="true"

image=mavenproject1
version=1.0-SNAPSHOT
latest=true

#OPTIONS="--no-cache --force-rm"
#OPTIONS="--no-cache"
#OPTIONS="--force-rm"
OPTIONS=""

docker build ${OPTIONS} -t vivekyopmail/${image}:1.0-SNAPSHOT .
if [ "$?" -eq 0 ] && [ ${deploy} == "true" ]; then
    docker push vivekyopmail/${image}:1.0-SNAPSHOT
    if [ "$latest" == "true" ]; then
        docker tag vivekyopmail/${image}:1.0-SNAPSHOT vivekyopmail/${image}:latest
        docker push vivekyopmail/${image}:latest
    fi
fi