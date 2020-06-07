[![Build Status](https://travis-ci.com/vokor/Chat.svg?branch=master)](https://github.com/vokor/Chat)
# Chat gRPC

## About
This is a simple chat using [gRPC](https://grpc.io/).
Peer-to-peer connection.
Important: names must be unique

### Build the project

         ./gradlew installDist
         
### Run the project

         cd build/libs
         java -jar Chat-1.0-Chat.jar
         
### Build docker

         docker build -t chat:demo .
         
### Run docker
As server, -p4004:4004 open 4004 port and we can use it for our connection:

         docker run -it -p4004:4004 chat:demo
         
As client, connect to the open port and server address:

         docker run -it chat:demo

### Requirements
* 11-openjdk
* gradle >= 5.0

## Contributors

* Korolikhin Vladimir
* Chubukov Filipp

