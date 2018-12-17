#!/bin/bash  
ps -ef | grep /home/microservice/service/nb-customer | grep -v grep | awk '{ print $2 }' | xargs kill -9