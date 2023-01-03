#!/bin/bash

docker build -t benjcady14/globetrotter:latest

docker run --name test-env-var -p 127.0.0.1:8080:8080 benjcady14/globetrotter:latest