#!/bin/sh
. /etc/profile
HS_BASE=/opt/app/takeaway

echo "[COBBLE.GE] cd ${HS_BASE} ..."
cd ${HS_BASE}

echo "[COBBLE.GE] Start git commit..."
git commit -m "From Compute, zzd."
echo "[COBBLE.GE] Finish git commit..."

echo "[COBBLE.GE] Start git fetch & merge..."
git fetch origin
git merge origin refs/heads/master -m "git merge from github"
echo "[COBBLE.GE] Finish git fetch & merge..."

echo "[COBBLE.GE] Start git push ... "
git push origin refs/heads/master
echo "[COBBLE.GE] Finish git push ..."
