-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- 主机: localhost
-- 生成日期: 2015 年 03 月 03 日 09:32
-- 服务器版本: 5.0.45
-- PHP 版本: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 数据库: `test`
-- 

-- --------------------------------------------------------

-- 
-- 表的结构 `sys_area`
-- 

CREATE TABLE `sys_area` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `path` varchar(300) default NULL,
  `description` varchar(200) default NULL,
  `order_num` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- 表的结构 `sys_org`
-- 

CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `path` varchar(200) default NULL COMMENT '存储id序列值，以“/”分隔',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `description` varchar(100) default NULL COMMENT '机构描述信息',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- 表的结构 `sys_role`
-- 

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '角色id',
  `name` varchar(16) NOT NULL COMMENT '角色名称',
  `description` varchar(64) default NULL COMMENT '角色描述',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- 表的结构 `sys_user`
-- 

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account` varchar(20) NOT NULL,
  `org_id` bigint(20) default NULL,
  `password` varchar(20) NOT NULL,
  `birthday` date NOT NULL,
  `createtime` datetime NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `modifytime` datetime NOT NULL,
  `status` tinyint(4) NOT NULL default '0' COMMENT '用户账户状态',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- 表的结构 `sys_user_role`
-- 

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '户用ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY  (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 
-- 限制导出的表
-- 

-- 
-- 限制表 `sys_user_role`
-- 
ALTER TABLE `sys_user_role`
  ADD CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  ADD CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`);
