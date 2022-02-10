用java写的自定义exporter
配置如下：

port: 服务启动端口

fileSize_pathNames: 目录名，用于统计该目录下所有文件及文件大小

portConnectionCount_Ports: 端口号，用于统计该端口的连接数

启动：java -jar custom-exporter.jar

查看：http://serverip:port/metrics