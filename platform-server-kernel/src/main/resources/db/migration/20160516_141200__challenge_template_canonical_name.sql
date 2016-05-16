ALTER TABLE `challenge_template` ADD `canonical_name` varchar(255) NOT NULL;
UPDATE `challenge_template` SET `canonical_name`= HEX(`id`);
ALTER TABLE `challenge_template` ADD CONSTRAINT `UK_lasqz791gha8yv1std` UNIQUE (`canonical_name`);