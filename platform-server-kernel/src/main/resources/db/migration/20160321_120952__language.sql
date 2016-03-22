--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `template` ADD `language_id` bigint(20) NOT NULL;
ALTER TABLE `template` ADD CONSTRAINT `FK_TEMPLATE_LANGUAGE` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`);

--
-- Table structure for table `task_language`
--

DROP TABLE IF EXISTS `task_language`;
CREATE TABLE `task_language` (
  `languages_id` bigint(20) NOT NULL,
  `tasks_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tasks_id`, `languages_id`),
  KEY `FK_TASK` (`tasks_id`),
  CONSTRAINT `FK_LANGUAGE` FOREIGN KEY (`languages_id`) REFERENCES `language` (`id`),
  CONSTRAINT `FK_TASK` FOREIGN KEY (`tasks_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;