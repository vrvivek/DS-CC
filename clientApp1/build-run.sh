#!/bin/sh
mvn clean package && docker build -t vivekyopmail/clientApp1 .
docker rm -f clientApp1 2>/dev/null || true && docker run -it --name clientApp1 -p 8080:8080 -p 4848:4848 -p 8181:8181 --name clientApp1 vivekyopmail/clientApp1
