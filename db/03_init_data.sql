use takeawaydb;

INSERT INTO `b_privilege` VALUES ('-1', 'PRIVILEGE_ALL', '/mgr/**');
INSERT INTO `b_role` VALUES ('-1', 'ROLE_ADMIN');
INSERT INTO `b_user` VALUES ('-1', 'admin', 'admin', '1');
INSERT INTO `rel_privilege_role` VALUES ('-1', '-1', '-1');
INSERT INTO `rel_user_role` VALUES ('-1', '-1', '-1');