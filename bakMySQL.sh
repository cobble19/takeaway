#!/bin/sh
##################
# bak MySQL db   #
##################
# 1. cp origin huasheng.sql
dateStr=`date +%y%m%d`
#cp takeawaydb.sql takeawaydb.sql.$dateStr.bak
mysqldump --socket=/tmp/mysql.sock -c -uroot -proot123 --default-character-set=utf8 takeawaydb > takeawaydb.$dateStr.sql

