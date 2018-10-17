#!/bin/bash  
ps -ef | grep nb-zuul | grep -v grep | awk '{ print $2 }' | xargs kill -9