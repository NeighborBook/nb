#!/bin/bash  
ps -ef | grep /home/microservice/service/nb-eureka-server | grep -v grep | awk '{ print $2 }' | xargs kill -9