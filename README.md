# Chat gRPC

## About
This is a simple chat using [gRPC](https://grpc.io/).
Peer-to-peer connection.
Important: names must be unique

### Build the project

         gradle wrapper
         ./gradlew installDist
         
### Run the project

         cd build/libs
         java -jar Chat-1.0-Chat.jar
         
### Build docker

         docker build -t chat:demo .
         
### Run docker

         run docker chat:demo

### Requirements
* 11-openjdk
* gradle >= 5.0

## Contributors

* Korolikhin Vladimir
* Chubukov Filipp

