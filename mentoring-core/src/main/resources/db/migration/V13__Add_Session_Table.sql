-- SESSIONS
CREATE TABLE `SESSIONS` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `CREATED_AT` DATETIME NOT NULL,
  `CREATED_BY` VARCHAR(50) NOT NULL,
  `LIFE_CYCLE_STATUS` VARCHAR(100) NOT NULL,
  `UPDATED_AT` DATETIME DEFAULT NULL,
  `UPDATED_BY` VARCHAR(50) DEFAULT NULL,
  `UUID` VARCHAR(50) NOT NULL,
  `START_DATE` DATETIME NOT NULL,
  `END_DATE` DATETIME NOT NULL,
  `PERFORMED_DATE` DATE NOT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `REASON` VARCHAR(255) DEFAULT 'NA',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- MENTORSHIPS
ALTER TABLE MENTORSHIPS ADD COLUMN `SESSION_ID` BIGINT(20);
ALTER TABLE MENTORSHIPS ADD CONSTRAINT `FK_SESSION_ID` FOREIGN KEY (`SESSION_ID`) REFERENCES `SESSIONS` (`ID`);
ALTER TABLE MENTORSHIPS DROP COLUMN REFERRED_MONTH;

-- FORMS
ALTER TABLE FORMS ADD COLUMN `TARGET` INT(11) NOT NULL DEFAULT 1;