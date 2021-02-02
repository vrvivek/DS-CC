#!/bin/sh
mvn clean package
docker build -t vivekyopmail/mavenproject1 .
