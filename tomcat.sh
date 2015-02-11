#!/bin/sh
# comment to support chkconfig
# chkconfig: 2345 100 50
# description: Easy to start, stop, restart, status tomcat

. /etc/profile

pid=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | awk '{ print $2 }'`
instance_number=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | wc -l`

start()
{
  echo -e "Starting tomcat...\n"
  instance_number=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | wc -l`
  if [ $instance_number -gt 0 ]; then
    echo -e "Tomcat is running..., you must first stop it...\n"
  else 
    sh ${CATALINA_HOME}/bin/startup.sh
  fi
  echo -e "Start tomcat finished...\n"
}

stop()
{
  echo -e "Stoping tomcat...\n"
  pid=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | awk '{ print $2 }'`
  instance_number=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | wc -l`
  if [ $instance_number -eq 0 ]; then
    echo -e "Tomcat is not running...\n"
  else
#   sh ${CATALINA_HOME}/bin/shutdown.sh
    echo -e "pid=$pid\n"
    kill -9 $pid > /dev/null 2>&1
  fi 
  echo -e "Stop tomcat finished...\n"
}

restart()
{
  echo -e "Restarting tomcat...\n"
  stop
  start
  echo -e "Restart tomcat finished...\n"
}

status()
{
  pid=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | awk '{ print $2 }'`
  instance_number=`ps -efww | grep "[c]atalina.home=/opt/apache-tomcat" | wc -l`
  echo -e "instance number=$instance_number \n"
  if [ $instance_number -eq 0 ]; then
    echo -e "Tomcat is not running...\n"
  else
    echo -e "Tomcat is running...\n"
  fi
}

usage()
{
  echo -e "\nUsage: sh $0 [statt | stop | restart | status]\n"
  echo -e "       Welcome to use it\n"
}

action=$1
case "${action}" in
  start)
  {
    start
  }
  ;;
  stop)
  {
    stop
  }
  ;;
  restart)
  {
    restart
  }
  ;;
  status)
  {
    status
  }
  ;;
  *)
  {
    usage
  }
  ;;
esac



