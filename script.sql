CREATE TABLE `courses` (
                           `course_name` bigint NOT NULL AUTO_INCREMENT COMMENT '课程名称',
                           `page_num` bigint NOT NULL COMMENT '第几集',
                           `title` varchar(100)  NOT NULL COMMENT '本集名称',
                           `duration` varchar(50) NOT NULL COMMENT '时长非格式化',
                           `minutes` int DEFAULT '0' NOT NULL COMMENT '时长分钟',
                           `href` varchar(50) NOT NULL COMMENT '本集url地址',
                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`course_name`) USING BTREE
) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='B站课程目录集';