CREATE SCHEMA `exposition_calendar_db` ;

CREATE TABLE `exposition_calendar_db`.`accounts` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) DEFAULT 'user',
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`account_id`));

CREATE TABLE `exposition_calendar_db`.`expohalls` (
  `expohall_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`expohall_id`));

CREATE TABLE `exposition_calendar_db`.`expositions` (
  `exposition_id` INT NOT NULL AUTO_INCREMENT,
  `exposition_name` VARCHAR(45) NOT NULL,
  `date_from` DATE NOT NULL,
  `date_to` DATE NOT NULL,
  `theme` VARCHAR(45) NOT NULL,
  `ticket_price` INT NOT NULL,
  PRIMARY KEY (`exposition_id`));

ALTER TABLE `exposition_calendar_db`.`expositions`
  ADD COLUMN `expohall_id` INT NOT NULL AFTER `ticket_price`;

CREATE TABLE `exposition_calendar_db`.`orders` (
  `account_id` INT NOT NULL,
  `expo_id` INT NOT NULL);


INSERT INTO `exposition_calendar_db`.`accounts` (`account_id`, `first_name`, `last_name`, `city`, `login`, `password`, `role`, `email`) VALUES ('1', 'mykola', 'klykov', 'kiev', 'root', 'root', 'admin', 'klykovnd@gmail.com');
