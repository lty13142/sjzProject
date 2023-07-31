微服务中数据库、redis、文件服务等地址建议使用host方式配置，便于动态切换服务地址<br/>

建议使用SwitchHosts.exe工具动态切换host<br/>

服务host配置，需要改成自己的ip和服务ip<br/>

```
# crcm-cloud
10.35.11.50 server-host
10.35.11.50 crcm-cloud-auth
10.150.1.18	crcm-cloud-mysql
127.0.0.1	crcm-cloud-redis
127.0.0.1	crcm-cloud-gateway
127.0.0.1	crcm-cloud-register
127.0.0.1	crcm-cloud-sentinel
127.0.0.1	crcm-cloud-monitor
127.0.0.1	crcm-upms-server
```