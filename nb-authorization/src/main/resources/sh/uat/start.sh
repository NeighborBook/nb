#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice-uat/service/nb-authorization/nb-authorization-1.0.0.jar --spring.profiles.active='uat, eureka_uat, db_ms_uat, redis_uat' --eureka.instance.ip-address=47.100.243.160 &