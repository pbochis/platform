ALTER TABLE `submission`
    ADD COLUMN `language_id` BINARY(16),
    ADD FOREIGN KEY `fk_submission_language`(`language_id`) REFERENCES `language` (`id`);