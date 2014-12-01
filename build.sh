#!/bin/sh
. /etc/profile
HS_BASE=/opt/app/takeaway
CATALINA_HOME=`echo ${CATALINA_HOME}`
echo "[COBBLE.GE] Stop tomcat..."
sh ${CATALINA_HOME}/bin/shutdown.sh
# bak upload dir
echo "[COBBLE.GE] Bak [upload] directory..."
## /bin/cp -R ${HS_BASE}/target/huasheng/upload/* ${HS_BASE}/temp/upload/

echo "[COBBLE.GE] cd ${HS_BASE} ..."
cd ${HS_BASE}
echo "[COBBLE.GE] Start git pull..."
git pull 
echo "[COBBLE.GE] Start mvn..."
mvn clean install -Dmaven.test.skip=true
/bin/cp -f ${HS_BASE}/target/takeaway/jdbc.release.properties ${HS_BASE}/target/takeaway/jdbc.properties
/bin/cp -Rf ${HS_BASE}/target/takeaway /opt/www
# Restore upload dir
echo "[COBBLE.GE] Restore [upload] directory..."
## mkdir -p ${HS_BASE}/target/huasheng/upload
## /bin/cp -R ${HS_BASE}/temp/upload/* ${HS_BASE}/target/huasheng/upload/
echo "[COBBLE.GE] Start tomcat..."
sh ${CATALINA_HOME}/bin/startup.sh
echo "[COBBLE.GE] Build finished..."

