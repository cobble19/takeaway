#!/bin/sh

. /etc/profile
HS_BASE=/opt/app/takeaway
CATALINA_HOME=`echo ${CATALINA_HOME}`
echo "[COBBLE.GE] Stop MySQL..."
service mysql stop
echo "[COBBLE.GE] Stop tomcat..."
# sh ${CATALINA_HOME}/bin/shutdown.sh
service tomcat stop
# bak upload dir
# echo "[COBBLE.GE] Bak [upload] directory..."
## /bin/cp -R ${HS_BASE}/target/huasheng/upload/* ${HS_BASE}/temp/upload/

echo "[COBBLE.GE] cd ${HS_BASE} ..."
cd ${HS_BASE}
#echo "[COBBLE.GE] Start git pull..."
#git pull 
echo "[COBBLE.GE] Start git fetch & merge..."
git fetch origin
git merge origin refs/heads/master -m "git merge from github"
echo "[COBBLE.GE] Finish git fetch & merge..."
echo "[COBBLE.GE] Start mvn..."
mvn clean install -Dmaven.test.skip=true
echo "[COBBLE.GE] Copy jdbc.properties"
/bin/cp -f ${HS_BASE}/target/takeaway/WEB-INF/classes/jdbc.release.properties ${HS_BASE}/target/takeaway/WEB-INF/classes/jdbc.properties
echo "cd ${HS_BASE}/target/takeaway/mgr/ta ..."
cd ${HS_BASE}/target/takeaway/mgr/ta
echo "Ext sencha app build ..."
sencha app build
echo "[COBBL.GE] Copy ${HS_BASE}/target/takeaway/mgr/ta/build/production/TA/* ${HS_BASE}/target/takeaway/mgr/ta ..."
/bin/cp -Rf ${HS_BASE}/target/takeaway/mgr/ta/build/production/TA/* ${HS_BASE}/target/takeaway/mgr/ta
echo "[COBBLE.GE] rm -rf /opt/app/www/takeaway..."
rm -rf /opt/app/www/takeaway/
echo "[COBBLE.GE]Copy ${HS_BASE}/target/takeaway /opt/app/www  ..."
/bin/cp -Rf ${HS_BASE}/target/takeaway /opt/app/www
# Restore upload dir
## echo "[COBBLE.GE] Restore [upload] directory..."
## mkdir -p ${HS_BASE}/target/huasheng/upload
## /bin/cp -R ${HS_BASE}/temp/upload/* ${HS_BASE}/target/huasheng/upload/
echo "[COBBLE.GE] Start MySQL..."
service mysql start
echo "[COBBLE.GE] Start tomcat..."
# sh ${CATALINA_HOME}i/bin/startup.sh
service tomcat start
echo "[COBBLE.GE] Build finished..."

