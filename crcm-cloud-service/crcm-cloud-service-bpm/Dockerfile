#使用jdk8作为基础镜像
FROM adoptopenjdk/openjdk8
#指定作者
MAINTAINER zzyt
# 设置系统语言为中文和UTF-8 并配置环境
ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV LANGUAGE zh_CN.UTF-8
#暴露容器的端口
EXPOSE 8901
#复制指定的jar为容器中的jar
ADD target/crcm-cloud-service-bpm.jar app.jar
#添加OA系统使用的字体
ADD oa-font/* /usr/share/fonts/
#解決字体问题（下载很慢）
##保证容器的时区和宿主机相同
#RUN apk add --update ttf-dejavu fontconfig
#同步服务器时区
ENV TZ=Asia/Shanghai
ENV host 0.0.0.0
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#在容器中用cmd命令执行jar包
ENTRYPOINT ["java","-jar","app.jar"]