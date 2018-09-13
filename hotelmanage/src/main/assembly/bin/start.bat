@echo off
@echo "开始启动jetty..."
cd %~dp0
cd ..
start /b java -jar hotelmanage.war #后台持续运行
@echo "jetty启动成功！"
exit