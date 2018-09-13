%生产环境打包 For Windows%
@echo off
mvn clean package -Pprod -Dmaven.test.skip=true