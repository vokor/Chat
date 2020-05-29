FROM ubuntu:16.04

COPY /build /build

WORKDIR /build/libs/

RUN apt-get update

RUN apt-get install software-properties-common -y

RUN add-apt-repository ppa:openjdk-r/ppa -y

RUN apt-get update

RUN apt-get install openjdk-11-jdk -y 

CMD java -jar Chat-1.0-Chat.jar

