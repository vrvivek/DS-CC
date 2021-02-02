#!/bin/sh
mvn clean package && docker build -t vivekyopmail/servicesApp .
docker rm -f servicesApp 2>/dev/null || true && docker run -it --name servicesApp -p 8080:8080 -p 4848:4848 -p 8181:8181 --name servicesApp vivekyopmail/servicesApp
