#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice/service/nb-authorization/nb-authorization-1.0.0.jar --spring.profiles.active='pd, eureka_pd, db_ms_pd, redis_pd' --eureka.instance.ip-address=47.100.243.160 &