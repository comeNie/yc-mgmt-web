1.������
#һ��Ҫ��clean
gradle clean
#Ȼ���ٴ��
gradle build -x test

2.���ɾ���
#�ڱ������ɴ�˽��ǰ׺�ľ���  (ÿ�δ���ǰ�汾��Ҫ����)
docker build -t 10.19.13.18:5000/yc-mgmt-web:v1.0 .
#���������͵�˽��
docker push 10.19.13.18:5000/yc-mgmt-web:v1.0

3. ���о���
#--net=host  ��ʾΪ����(host)ģʽ  ȥ�������ã�Ĭ��Ϊ�Ž�(bridge)ģʽ
#-e ������Ҫ���õĻ�������
docker run -d --name yc-mgmt-web-v1.0  -p 14120:8080  \
-e "casServerLoginUrl=http://10.19.13.19:14125/login"  \
-e "casServerUrlPrefix=http://10.19.13.19:14125"   \
-e "serverName=http://10.19.13.19:14120"   \
-e "logOutServerUrl=http://10.19.13.19:14125/logout"   \
-e "logOutBackUrl=http://10.19.13.19:14125"   \
-e "casServerLoginUrl_Inner=http://10.19.13.19:14125/login"  \
-e "casServerUrlPrefix_Inner=http://10.19.13.19:14125"   \
-e "serverName_Inner=http://10.19.13.19:14120"   \
-e "logOutServerUrl_Inner=http://10.19.13.19:14125/logout"   \
-e "logOutBackUrl_Inner=http://10.19.13.19:14125"   \ 
-e "innerDomains=changhong.com" \
-e "SDK_MODE=0" \
-e "PAAS_AUTH_URL=http://10.1.245.4:19811/service-portal-uac-web/service/auth" \
-e "PAAS_AUTH_PID=D14F7D708109471AB6F3084B2ABAE9A6" \
-e "PAAS_CCS_ID=CCS011" \
-e "PAAS_CCS_PWD=123456" \
-e "REST_REGISTRY_ADDR=10.19.13.13:29181"  \
-e "whitelist=yiyun.com" \
10.19.13.18:5000/yc-mgmt-web:v1.0

#�鿴����������־
docker logs yc-mgmt-web-v1.0
#�����������鿴�����ڲ������
docker exec -it yc-mgmt-web-v1.0 /bin/bash
#ɾ�����е�����
docker rm -fv yc-mgmt-web-v1.0

#=============������־========================#
*2016-09-23
1����ʼ���