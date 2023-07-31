#sql的登录用户名和密码
mysql_user="用户名"
mysql_password="密码"
mysql_host="IP地址"
mysql_port="3306"
mysql_charset="utf8mb4"
# 备份文件存放地址
backup_location=/app/storage/mysql/backup/data
# 是否删除过期数据
expire_backup_delete="ON"
expire_days=7
backup_time=`date +%Y%m%d%H%M`
backup_dir=$backup_location
# 备份指定数据库中数据
docker exec -i firemysql mysqldump -h$mysql_host -P$mysql_port -u$mysql_user -p$mysql_password -B firemanagement --set-gtid-purged=OFF > $backup_dir/项目_$backup_time.sql
echo "备份文件路径: $backup_dir/项目_$backup_time.sql"
# 删除过期数据
if [ "$expire_backup_delete" == "ON" -a  "$backup_location" != "" ];then
`find $backup_location/ -type f -mtime +$expire_days | xargs rm -rf`
echo "已清理过期的数据库备份文件..."
fi
#创建定时任务
#crontab -e
#0 3 * * * cd /app/storage/mysql/backup;sh mysql_backup.sh >> /app/storage/mysql/backup/backup_logs.log
#crontab -l