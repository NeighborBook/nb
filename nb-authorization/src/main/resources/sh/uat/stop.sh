#!/bin/bash  
ps -ef | grep /home/microservice-uat/service/nb-authorization | grep -v grep | awk '{ print $2 }' | xargs kill -9