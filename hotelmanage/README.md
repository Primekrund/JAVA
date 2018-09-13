#运行说明
开发时运行LauncherDev的main函数
打包后在target目录中生成iHotel-standalone.zip，解压后运行iHotel/bin/start.sh或start.bat

#jenkens构建完成后需要执行的shell
#BUILD_ID,必须的，否则运行进程会闪退
export BUILD_ID=iHotel

#导入环境变量，以便使用jdk1.8
source /etc/profile 

sleep 3s

#停止所有正在运行的 iHotel 项目

#查找用java运行的iHotel程序进程ID，并结束进程
#执行可能失败的命令时要或一个可以成功返回exit_code=0的命令，否则jenkins会停止执行后面的命令

id=`ps -ef | pgrep -f "iHotel" || echo "0"`

if [ $id -ne 0 ]; then

	echo "find pid: $id"
    
    echo "kill pid $id ......"
    
	kill -9 $id
    
    echo "complete to kill pid $id !!!"
else

	echo "can not find pid for iHotel......"

fi



sleep 3s

#删除原来的zip包和zip解压的目录，删除前先判断目录或文件是否存在，若存在则删除
cd /home/krund/standalone

if [ -d "iHotel" ]
	then rm -rf /home/krund/standalone/iHotel/
fi;

if [ -f "iHotel-standalone.zip" ]
	then rm /home/krund/standalone/iHotel-standalone.zip
fi;

sleep 5s

#复制新的zip包
cp ${WORKSPACE}/target/iHotel-standalone.zip /home/krund/standalone

sleep 10s

#解压zip
unzip /home/krund/standalone/iHotel-standalone.zip

sleep 10s

#启动jetty
sh /home/krund/standalone/iHotel/bin/start.sh