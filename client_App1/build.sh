#!/bin/sh
mvn clean package
docker build -t vivekyopmail/client_App1 .
