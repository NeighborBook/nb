#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice/service/nb-zuul/nb-zuul-1.0.0.jar --spring.profiles.active='pd, eureka_pd' --eureka.instance.ip-address=47.100.243.160 &