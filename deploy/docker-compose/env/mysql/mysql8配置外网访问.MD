use mysql;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '密码';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '密码';
FLUSH PRIVILEGES;
CREATE USER 'repl'@'%' IDENTIFIED WITH mysql_native_password BY '密码';
grant replication client,replication slave on *.* to 'repl'@'%';