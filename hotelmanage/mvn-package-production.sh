#!/bin/sh
#开发环境打包 For Linux
mvn clean package -Pprod -Dmaven.test.skip=true