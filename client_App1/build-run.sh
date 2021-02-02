#!/bin/sh
mvn clean package && docker build -t vivekyopmail/client_App1 .
docker rm -f client_App1 2>/dev/null || true && docker run -it --name client_App1 -p 8080:8080 -p 4848:4848 -p 8181:8181 --name client_App1 vivekyopmail/client_App1
