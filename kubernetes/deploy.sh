#!/bin/bash

echo "Enter tag (#.#.#)"

read tag

IMAGE_NAME=benjcady/globetrotter:$tag

docker build -t $IMAGE_NAME ../.

docker push $IMAGE_NAME

kubectl create namespace gt-weather

kubectl apply -f deployment.yaml

kubectl apply -f service.yaml

kubectl get po -n gt-weather -w