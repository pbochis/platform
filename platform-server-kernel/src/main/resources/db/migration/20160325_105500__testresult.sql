--
-- Table structure for table `test_result`
--
DROP TABLE IF EXISTS `test_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_result` (
  `id` binary(16) NOT NULL,
  `green` bit(1) NOT NULL,
  `submission_id` binary(16) NOT NULL,
  `test_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmcokumtauiop1p52ox5l19np9` (`submission_id`),
  KEY `FKef3e8k7fgvkj4mox0lxrkf8hh` (`test_id`),
  CONSTRAINT `FKef3e8k7fgvkj4mox0lxrkf8hh` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`),
  CONSTRAINT `FKmcokumtauiop1p52ox5l19np9` FOREIGN KEY (`submission_id`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
