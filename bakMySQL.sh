#!/bin/sh
##################
# bak MySQL db   #
##################
# 1. cp origin huasheng.sql
cp takeawaydb.sql takeawaydb.sql.bak
mysqldump --socket=/tmp/mysql.sock -c -uroot -proot123 --default-character-set=utf8 takeawaydb > takeawaydb.sql

