#!/bin/sh

# 使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [port|base|modules|stop|rm]"
	exit 1
}

# 开启所需端口
port(){
	firewall-cmd --add-port=80/tcp --permanent
	firewall-cmd --add-port=8848/tcp --permanent
	firewall-cmd --add-port=8850/tcp --permanent
	firewall-cmd --add-port=8868/tcp --permanent
	firewall-cmd --add-port=8851/tcp --permanent
	firewall-cmd --add-port=8852/tcp --permanent
	firewall-cmd --add-port=8899/tcp --permanent
	service firewalld restart
}

# 启动基础环境（必须）
base(){
	docker-compose up -d mysql redis nacos
}

# 启动程序模块（必须）
services(){
	docker-compose up -d gateway auth admin file monitor
}

# 关闭所有环境/模块
stop(){
	docker-compose stop
}

# 删除所有环境/模块
rm(){
	docker-compose down
}


# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"base")
	base
;;
"services")
	modules
;;
"stop")
	stop
;;
"rm")
	rm
;;
*)
	usage
;;
esac
