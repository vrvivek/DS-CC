#!/bin/sh
mvn clean package && docker build -t vivekyopmail/mavenproject1 .
docker rm -f mavenproject1 2>/dev/null || true && docker run -it --name mavenproject1 -p 8080:8080 -p 4848:4848 -p 8181:8181 --name mavenproject1 vivekyopmail/mavenproject1
