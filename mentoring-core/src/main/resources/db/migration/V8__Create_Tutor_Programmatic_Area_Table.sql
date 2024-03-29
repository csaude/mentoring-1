CREATE TABLE `TUTOR_PROGRAMMATIC_AREA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_AT` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LIFE_CYCLE_STATUS` varchar(100) NOT NULL,
  `UPDATED_AT` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(50) DEFAULT NULL,
  `UUID` varchar(50) NOT NULL,
  `PROGRAMMATIC_AREA_ID` bigint(20) NOT NULL,
  `TUTOR_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_mrq1e9mwif0mx0fttgwrx800c` (`TUTOR_ID`,`PROGRAMMATIC_AREA_ID`),
  KEY `FK_f57yc0th2si3rnnjaxj5qmmf5` (`PROGRAMMATIC_AREA_ID`),
  CONSTRAINT `FK_f57yc0th2si3rnnjaxj5qmmf5` FOREIGN KEY (`PROGRAMMATIC_AREA_ID`) REFERENCES `PROGRAMMATIC_AREAS` (`ID`),
  CONSTRAINT `FK_hvq73lwjrb739xp603wbbmqp8` FOREIGN KEY (`TUTOR_ID`) REFERENCES `TUTORS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;