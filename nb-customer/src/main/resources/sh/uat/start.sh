#!/bin/bash  
nohup java -Xmx500m -jar /home/microservice-uat/service/nb-customer/nb-customer-1.0.0.jar --spring.profiles.active='uat, db_nb_uat,redis_uat, eureka_uat, weixin_uat' --eureka.instance.ip-address=47.100.243.160 &