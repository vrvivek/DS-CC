#!/bin/sh
mvn clean package
docker build -t vivekyopmail/servicesApp .
