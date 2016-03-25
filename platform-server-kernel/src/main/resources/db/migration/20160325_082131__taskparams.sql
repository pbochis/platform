--
-- Table structure for table `test_params`
--

DROP TABLE IF EXISTS `task_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_params` (
  `task_id` binary(16) NOT NULL,
  `params` longtext,
  `params_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`task_id`,`params_KEY`),
  CONSTRAINT `FK_PARAMS_TASK` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;