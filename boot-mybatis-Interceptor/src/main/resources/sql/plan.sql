CREATE TABLE `plan` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `plan_name` varchar(255) DEFAULT NULL COMMENT '计划名称',
                        `plan_content` varchar(255) DEFAULT NULL COMMENT '计划内容',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `create_by` varchar(60) DEFAULT NULL COMMENT '创建人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `update_by` varchar(60) DEFAULT NULL COMMENT '更新人',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;