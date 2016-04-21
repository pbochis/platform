--
-- Table structure for table `team_invitation`
--

DROP TABLE IF EXISTS `team_invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_invitation` (
  `team_id` binary(16) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `invited_by_id` binary(16) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`team_id`,`user_id`),
  KEY `FKg24qjftfifissdfgilscl0vmr42` (`user_id`),
  CONSTRAINT `FK9ubp79ei4tv4crasdf2r9n7u5i6e` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKg24qjftfifissdfgilscl0vmr42` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKg24qjftfifisxhasdfscl0acsb1` FOREIGN KEY (`invited_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_user`
--

DROP TABLE IF EXISTS `team_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_user` (
  `invited_team_id` binary(16) NOT NULL,
  `invited_user_id` binary(16) NOT NULL,
  PRIMARY KEY (`invited_team_id`,`invited_user_id`),
  KEY `FK7oa8832sdfgva1n7a20jt0y12` (`invited_user_id`),
  CONSTRAINT `FK4n330drdqh3fvugsdfgsdfgeeaub12x` FOREIGN KEY (`invited_team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK7oa8832sdfgva1n7a20jt0y12` FOREIGN KEY (`invited_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;