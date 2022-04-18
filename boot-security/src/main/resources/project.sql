SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_res
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_res`;
CREATE TABLE `t_sys_res`  (
                              `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
                              `name` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
                              `res_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源路径',
                              `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源code',
                              `res_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0菜单   1按钮',
                              `pid` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级id',
                              `create_user` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                              `create_date` datetime DEFAULT NULL,
                              `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-资源表【菜单】' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_res
-- ----------------------------
INSERT INTO `t_sys_res` VALUES ('101', '用户添加', '/tSysUser/add', '/tSysUser/add', '1', '100', NULL, NULL, '');
INSERT INTO `t_sys_res` VALUES ('100', '用户管理', '/tSysUser/findByPage', '/tSysUser/findByPage', '0', '', NULL, NULL, 'fa fa-snowflake-o');
INSERT INTO `t_sys_res` VALUES ('102', '用户修改', '/tSysUser/edit', '/tSysUser/edit', '1', '100', NULL, NULL, 'fa fa-home');
INSERT INTO `t_sys_res` VALUES ('103', '用户删除', '/tSysUser/delete', '/tSysUser/delete', '1', '100', NULL, NULL, 'fa fa-home');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
                               `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
                               `role_name` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                               `role_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
                               `create_user` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
                               `create_date` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('277144172094291968', '部门经理', '部门经理', NULL, '2021-08-27 10:22:51');
INSERT INTO `t_sys_role` VALUES ('277144675716956160', '普通员工', '普通员工', NULL, '2021-08-27 10:24:51');
INSERT INTO `t_sys_role` VALUES ('277646459757658112', '测试员工', '测试员工', '', '2021-08-28 19:38:46');

-- ----------------------------
-- Table structure for t_sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_res`;
CREATE TABLE `t_sys_role_res`  (
                                   `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                   `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
                                   `res_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-角色资源表中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_role_res
-- ----------------------------
INSERT INTO `t_sys_role_res` VALUES ('1234', '277144675716956160', '101');
INSERT INTO `t_sys_role_res` VALUES ('12345', '277144675716956160', '100');
INSERT INTO `t_sys_role_res` VALUES ('123456', '277144172094291968', '100');
INSERT INTO `t_sys_role_res` VALUES ('123457', '277144172094291968', '101');
INSERT INTO `t_sys_role_res` VALUES ('123458', '277144172094291968', '102');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
                               `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                               `username` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                               `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                               `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
                               `cell_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
                               `mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件',
                               `birthday` datetime DEFAULT NULL COMMENT '生日',
                               `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态（0-正常，1-删除，2-禁用）',
                               `account_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '1系统账号 2客户账号',
                               `invite_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邀请码',
                               `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别：0男 1女',
                               `up_num` int(11) DEFAULT NULL COMMENT '所有获赞总量',
                               `read_num` int(11) DEFAULT NULL COMMENT '所有文章阅读总量',
                               `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
                               `create_user` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
                               `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                               `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户签名',
                               `picture_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('276866508766838784', 'liqingwei', 'E10ADC3949BA59ABBE56E057F20F883E', '李庆伟', '15801174628', 'liqingwei01@cnpc.cn', NULL, '0', '1', '3781941dd39446b89e98cb661766aade', NULL, 0, 0, NULL, NULL, '2021-08-26 15:59:31', NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('277657251341139964', 'zhangsan', 'E10ADC3949BA59ABBE56E057F20F883E', '张三', '15801174626', 'zhangsan@qq.com', '2021-08-04 00:00:00', '0', '1', 'ea819ca462914e938943752d48ae8bcc', '0', 0, 0, '河南北京北', 'admin', '2021-08-28 20:21:38', '啥也不是。。。', '287348042074423296');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
                                    `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                    `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                    `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-用户角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('111', '276866508766838784', '277144675716956160');
INSERT INTO `t_sys_user_role` VALUES ('222', '277657251341139964', '277144172094291968');

SET FOREIGN_KEY_CHECKS = 1;

