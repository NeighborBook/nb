#!/bin/bash  
ps -ef | grep nb-eureka-server | grep -v grep | awk '{ print $2 }' | xargs kill -9