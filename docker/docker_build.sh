#!/bin/sh

# Setting Versions
VERSION='0.0.1'

cd ..
./gradlew clean build -x test
SHELL_PATH=`pwd -P`
echo $SHELL_PATH

cd $SHELL_PATH/product && docker build -t product:$VERSION .

cd $SHELL_PATH/user && docker build -t user:$VERSION .

cd $SHELL_PATH/nginx  && docker build -t nginx:$VERSION .
