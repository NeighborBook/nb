#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice-uat/service/nb-zuul/nb-zuul-1.0.0.jar --spring.profiles.active='uat, eureka_uat' --eureka.instance.ip-address=47.100.243.160 &