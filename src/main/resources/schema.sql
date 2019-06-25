-- 用户表
CREATE TABLE IF NOT EXISTS `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '应用ID',
  `platform` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '客户端类型',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否匿名，0-非匿名，1-匿名',
  `gender` tinyint(3) NOT NULL DEFAULT 0 COMMENT '性别，0-未设置，1-男，2-女，3-保密',
  `avatar` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `country` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '国家',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表';


-- 注册用户表
CREATE TABLE IF NOT EXISTS `user_by_account`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '应用ID',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `email` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '注册用户邮箱',
  `password` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `created_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `updated_at` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户正式表';