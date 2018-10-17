#!/bin/bash  
ps -ef | grep nb-authorization | grep -v grep | awk '{ print $2 }' | xargs kill -9