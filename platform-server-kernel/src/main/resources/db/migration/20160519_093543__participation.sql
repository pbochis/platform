--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participation` (
  `user_id` binary(16) NOT NULL,
  `challenge_id` binary(16) NOT NULL,
  `team_id` binary(16) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`challenge_id`,`user_id`),
  KEY `FKfputwcduinudasn7es02c12ra` (`user_id`),
  KEY `FK21uy087hrh8wxh7ky31c611yg` (`team_id`),
  CONSTRAINT `FK21uy087hrh8wxh7ky31c611yg` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKfputwcduinudasn7es02c12ra` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjmy4q5mb7he0w6qao8lho8hpk` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `participation`(challenge_id, user_id, created) SELECT challenge_id, user_id, now() from challenge_registered_user;
DROP TABLE IF EXISTS `challenge_registered_user`;
