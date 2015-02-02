CREATE DATABASE /*!32312 IF NOT EXISTS*/`takeawaydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

GRANT SUPER ON takeawaydb.* TO 'usertest'@'%' IDENTIFIED BY 'usertest';
GRANT SUPER ON takeawaydb.* TO 'usertest'@'localhost' IDENTIFIED BY 'usertest';

GRANT ALL PRIVILEGES ON takeawaydb.* TO 'usertest'@'%' IDENTIFIED BY 'usertest' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON takeawaydb.* TO 'usertest'@'localhost' IDENTIFIED BY 'usertest' WITH GRANT OPTION;
FLUSH PRIVILEGES;
