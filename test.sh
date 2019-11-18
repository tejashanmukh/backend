#!/bin/bash

# download the node modules
# npm install

# remove the container if exists or running 
if [ $(docker container ls -q -a --filter name=mybackend_container) != '' ]; then
    docker container stop mybackend_container
    docker container rm mybackend_container
fi

# remove the image if exists
if [ $(docker image ls -q --filter reference=mybackend) != '' ]; then
    docker image rm mybackend
fi

# build the image
docker build -t mybackend .

# start the container
docker run -itd -p 8000:4000 --name mybackend_container mybackend