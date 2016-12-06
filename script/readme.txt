1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test

2.生成镜像
#在本地生成带私服前缀的镜像  (每次打镜像前版本号要更新)
docker build -t 10.19.13.18:5000/yc-mgmt-web:v1.0 .
#将镜像推送到私服
docker push 10.19.13.18:5000/yc-mgmt-web:v1.0

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
docker run -d --name yc-mgmt-web  -p 24120:8080  \
-e "casServerLoginUrl=http://10.19.13.26:14125/login"  \
-e "casServerUrlPrefix=http://10.19.13.26:14125"   \
-e "serverName=http://10.19.13.26:24120"   \
-e "logOutServerUrl=http://10.19.13.26:14125/logout"   \
-e "logOutBackUrl=http://10.19.13.26:14125"   \
-e "casServerLoginUrl_Inner=http://10.19.13.26:14125/login"  \
-e "casServerUrlPrefix_Inner=http://10.19.13.26:14125"   \
-e "serverName_Inner=http://10.19.13.26:24120"   \
-e "logOutServerUrl_Inner=http://10.19.13.26:14125/logout"   \
-e "logOutBackUrl_Inner=http://10.19.13.26:14125"   \
-e "serverContextPath=" \
-e "SDK_MODE=0" \
-e "PAAS_AUTH_URL=http://10.1.245.4:19811/service-portal-uac-web/service/auth" \
-e "PAAS_AUTH_PID=5290E1BD25E84CE6B5B42CFAE0E4BFB0" \
-e "PAAS_CCS_ID=CCS007" \
-e "PAAS_CCS_PWD=123456" \
-e "REST_REGISTRY_ADDR=10.19.13.13:29181"  \
-e "whitelist=yiyun.com" \
10.19.13.36:5000/yc/yc-mgmt-web:v1.0_12

#查看镜像启动日志
docker logs yc-mgmt-web
#进入容器，查看镜像内部的情况
docker exec -it yc-mgmt-web /bin/bash
#删除运行的容器
docker rm -fv yc-mgmt-web

#=============更新日志========================#
*2016-09-23
1）初始打包
