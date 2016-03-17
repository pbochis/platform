-- MySQL dump 10.16  Distrib 10.1.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: test_platform
-- ------------------------------------------------------
-- Server version	10.1.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `canonical_name` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `inviteOnly` bit(1) NOT NULL,
  `challenge_name` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `challengeTemplate_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhcqo7gruoaecf5mmkloq5ldct` (`challengeTemplate_id`),
  CONSTRAINT `FKhcqo7gruoaecf5mmkloq5ldct` FOREIGN KEY (`challengeTemplate_id`) REFERENCES `challenge_template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `challenge_template`
--

DROP TABLE IF EXISTS `challenge_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext NOT NULL,
  `duration` bigint(20) NOT NULL,
  `instructions` longtext NOT NULL,
  `name` varchar(255) NOT NULL,
  `endpoint_id` bigint(20) DEFAULT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd5j69ht6utnlqcwat8q6nk8wq` (`endpoint_id`),
  KEY `FKtayntwqmm8ntlot76mmoonfio` (`organization_id`),
  CONSTRAINT `FKd5j69ht6utnlqcwat8q6nk8wq` FOREIGN KEY (`endpoint_id`) REFERENCES `endpoint` (`id`),
  CONSTRAINT `FKtayntwqmm8ntlot76mmoonfio` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `challenge_template_task`
--

DROP TABLE IF EXISTS `challenge_template_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge_template_task` (
  `challengeTemplates_id` bigint(20) NOT NULL,
  `tasks_id` bigint(20) NOT NULL,
  `tasks_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`challengeTemplates_id`,`tasks_ORDER`),
  KEY `FKr1xioakulcpwam6vtrhdv3hx8` (`tasks_id`),
  CONSTRAINT `FKpm5h2fhdqxbqcyoboajp8sm2l` FOREIGN KEY (`challengeTemplates_id`) REFERENCES `challenge_template` (`id`),
  CONSTRAINT `FKr1xioakulcpwam6vtrhdv3hx8` FOREIGN KEY (`tasks_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `challenge_user`
--

DROP TABLE IF EXISTS `challenge_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge_user` (
  `invitedChallenges_id` bigint(20) NOT NULL,
  `invitedUsers_id` bigint(20) NOT NULL,
  PRIMARY KEY (`invitedChallenges_id`,`invitedUsers_id`),
  KEY `FK7oa88321e99va1n7a20jt0y22` (`invitedUsers_id`),
  CONSTRAINT `FK4n330drdqh3fvuu6oaeeaubjw` FOREIGN KEY (`invitedChallenges_id`) REFERENCES `challenge` (`id`),
  CONSTRAINT `FK7oa88321e99va1n7a20jt0y22` FOREIGN KEY (`invitedUsers_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coderprofile`
--

DROP TABLE IF EXISTS `coderprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coderprofile` (
  `user_id` bigint(20) NOT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coderprofile_skillmap`
--

DROP TABLE IF EXISTS `coderprofile_skillmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coderprofile_skillmap` (
  `CoderProfile_user_id` bigint(20) NOT NULL,
  `skillMap` double DEFAULT NULL,
  `skillMap_KEY` int(11) NOT NULL,
  PRIMARY KEY (`CoderProfile_user_id`,`skillMap_KEY`),
  CONSTRAINT `FKqdt1d7fvegqwl6vfej9qs61kw` FOREIGN KEY (`CoderProfile_user_id`) REFERENCES `coderprofile` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `endpoint`
--

DROP TABLE IF EXISTS `endpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endpoint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `component` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_94ygu9u957rtltu5sxjonfejc` (`component`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `token` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `expire` datetime NOT NULL,
  `challenge_id` bigint(20) NOT NULL,
  PRIMARY KEY (`token`),
  KEY `FKp4ua0egpb823cstofc0a983w1` (`challenge_id`),
  CONSTRAINT `FKp4ua0egpb823cstofc0a983w1` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `nick` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oyl3rdscpk4622gyovnhddo6k` (`nick`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organization_member`
--

DROP TABLE IF EXISTS `organization_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_member` (
  `admin` bit(1) NOT NULL,
  `created` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  PRIMARY KEY (`organization_id`,`user_id`),
  KEY `FKs7b5ea4u5y4ds9vebvq7pxpa8` (`user_id`),
  CONSTRAINT `FKkta3960iv2gi5rtadvyyp046g` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FKs7b5ea4u5y4ds9vebvq7pxpa8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `finished` datetime DEFAULT NULL,
  `started` datetime DEFAULT NULL,
  `challenge_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj1mdhxodh10lmj3o9uh17i5ea` (`challenge_id`),
  KEY `FKpjjrrf0483ih2cvyfmx70a16b` (`user_id`),
  CONSTRAINT `FKj1mdhxodh10lmj3o9uh17i5ea` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`),
  CONSTRAINT `FKpjjrrf0483ih2cvyfmx70a16b` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `result_starttimes`
--

DROP TABLE IF EXISTS `result_starttimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_starttimes` (
  `Result_id` bigint(20) NOT NULL,
  `startTimes` datetime DEFAULT NULL,
  `startTimes_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`Result_id`,`startTimes_ORDER`),
  CONSTRAINT `FKj4dnfeadwrb81rv0o2noppxqp` FOREIGN KEY (`Result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `runner`
--

DROP TABLE IF EXISTS `runner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `runner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8cn4mdb6cxjdq2dvlaaw4et60` (`result_id`),
  KEY `FKh66q0hdbqk19lop36gyg3hvg0` (`task_id`),
  CONSTRAINT `FK8cn4mdb6cxjdq2dvlaaw4et60` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`),
  CONSTRAINT `FKh66q0hdbqk19lop36gyg3hvg0` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext NOT NULL,
  `duration` bigint(20) NOT NULL,
  `instructions` longtext NOT NULL,
  `name` varchar(255) NOT NULL,
  `isPublic` bit(1) DEFAULT NULL,
  `endpoint_id` bigint(20) DEFAULT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  `runner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhmkv7bsyrl43gufb23c1g1win` (`endpoint_id`),
  KEY `FKlr7r6n58jgscnls4brj8c6fg2` (`organization_id`),
  KEY `FKiu5jr4nqt9yey18c721ixwyud` (`runner_id`),
  CONSTRAINT `FKhmkv7bsyrl43gufb23c1g1win` FOREIGN KEY (`endpoint_id`) REFERENCES `endpoint` (`id`),
  CONSTRAINT `FKiu5jr4nqt9yey18c721ixwyud` FOREIGN KEY (`runner_id`) REFERENCES `runner` (`id`),
  CONSTRAINT `FKlr7r6n58jgscnls4brj8c6fg2` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task_skillmap`
--

DROP TABLE IF EXISTS `task_skillmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_skillmap` (
  `Task_id` bigint(20) NOT NULL,
  `skillMap` double DEFAULT NULL,
  `skillMap_KEY` int(11) NOT NULL,
  PRIMARY KEY (`Task_id`,`skillMap_KEY`),
  CONSTRAINT `FKkpyx9lkb064i8nhs8f3o6qv79` FOREIGN KEY (`Task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g2l9qqsoeuynt4r5ofdt1x2td` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_member`
--

DROP TABLE IF EXISTS `team_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_member` (
  `admin` bit(1) NOT NULL,
  `created` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`,`user_id`),
  KEY `FKg24qjftfifisxhilscl0vmrb1` (`user_id`),
  CONSTRAINT `FK9ubp79ei4tv4crd0r9n7u5i6e` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKg24qjftfifisxhilscl0vmrb1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxka5mfo042a51q4c79yfhl27` (`task_id`),
  CONSTRAINT `FKhxka5mfo042a51q4c79yfhl27` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `runner_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjvtfp7jnalo9d5em8rmpxbj68` (`runner_id`),
  KEY `FK2xx1yyitp3uqqmhgeg0qwuvps` (`task_id`),
  CONSTRAINT `FK2xx1yyitp3uqqmhgeg0qwuvps` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
  CONSTRAINT `FKjvtfp7jnalo9d5em8rmpxbj68` FOREIGN KEY (`runner_id`) REFERENCES `runner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test_params`
--

DROP TABLE IF EXISTS `test_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_params` (
  `Test_id` bigint(20) NOT NULL,
  `params` longtext,
  `params_KEY` varchar(255) NOT NULL,
  PRIMARY KEY (`Test_id`,`params_KEY`),
  CONSTRAINT `FKdfmjm368bkaw1knkqq7mxpiqh` FOREIGN KEY (`Test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin` bit(1) NOT NULL,
  `created` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `coder_profile` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKmh1qpwl7m9ht39vgkf37jbd7g` (`coder_profile`),
  CONSTRAINT `FKmh1qpwl7m9ht39vgkf37jbd7g` FOREIGN KEY (`coder_profile`) REFERENCES `coderprofile` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-17 19:10:57
