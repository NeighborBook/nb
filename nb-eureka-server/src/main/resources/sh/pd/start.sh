#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice/service/nb-eureka-server/nb-eureka-server-1.0.0.jar --spring.profiles.active='pd' --eureka.instance.ip-address=47.100.243.160 &