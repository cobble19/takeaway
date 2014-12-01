#!/bin/sh
. /etc/profile
CATALINA_HOME=`echo ${CATALINA_HOME}`
echo "Stop tomcat..."
sh ${CATALINA_HOME}/bin/shutdown.sh
echo "Start tomcat..."
sh ${CATALINA_HOME}/bin/startup.sh
echo "build finished..."

