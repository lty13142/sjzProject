#使用jdk8作为基础镜像
FROM adoptopenjdk/openjdk8
#指定作者
MAINTAINER zzyt
#docker镜像构建时的参数
#镜像端口
ARG PORT
#jar路径
ARG JAR_PATH
# 设置系统语言为中文和UTF-8 并配置环境
ENV LANG zh_CN.UTF-8
ENV LC_ALL zh_CN.UTF-8
ENV LANGUAGE zh_CN.UTF-8
#暴露容器的端口
EXPOSE $PORT
#复制指定的jar为容器中的jar
ADD $JAR_PATH app.jar
#解決字体问题（下载很慢）
##保证容器的时区和宿主机相同
#RUN apk add --update ttf-dejavu fontconfig
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
##保证log日志的时区和宿主机相同
RUN echo "Asia/Shanghai" > /etc/timezone
#在容器中用cmd命令执行jar包
ENTRYPOINT ["java","-jar","app.jar"]
