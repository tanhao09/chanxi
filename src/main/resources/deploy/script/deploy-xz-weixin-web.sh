#!/bin/bash

echo [INFO] "正在更新源代码..."
cd /opt/src/xiaozhu-h5/
git pull
git reset --hard

echo [INFO] "正在编译项目..."
mvn clean package -Dmaven.test.skip=true

echo [INFO] "正在部署..."
cp -rf ./target/xiaozhu-h5.war /opt/webapps/xiaozhu-h5/ROOT.war

echo [INFO] "正在重置 jetty 服务器..."
/opt/conf/jetty/xiaozhu-h5/jetty.sh restart

echo [INFO] "操作完成！"
