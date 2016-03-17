DROP TABLE IF EXISTS `challenge_template`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `challenge`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;