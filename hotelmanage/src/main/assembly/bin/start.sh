#!/bin/sh
echo "开始启动jetty..."
baseDirForScriptSelf=$(cd "$(dirname "$0")"; pwd)
echo "full path to currently executed script is : ${baseDirForScriptSelf}"
cd ${baseDirForScriptSelf}
cd ..
pwd
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8084 -jar hotelmanage.war & #后台持续运行
echo "jetty启动成功！"
exit