#!/bin/bash  
ps -ef | grep nb-customer | grep -v grep | awk '{ print $2 }' | xargs kill -9