-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: assignmentdb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema assignmentdb
-- -----------------------------------------------------
#DROP SCHEMA IF EXISTS `assignmentdb` ;

-- -----------------------------------------------------
-- Schema assignmentdb
-- -----------------------------------------------------
#CREATE SCHEMA IF NOT EXISTS `assignmentdb` DEFAULT CHARACTER SET utf8 ;
USE `assignmentdb` ;

-- -----------------------------------------------------
-- Table `assignmentdb`.`Template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Template` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`Template` (
  `templateID` INT NOT NULL AUTO_INCREMENT,
  `templateName` VARCHAR(100) NOT NULL UNIQUE,
  `file` LONGBLOB NOT NULL,
  PRIMARY KEY (`templateID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `assignmentdb`.`Client Profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Client Profile` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`Client Profile` (
  `Processing Details` VARCHAR(250) NULL,
  `Unique Identifier` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Date of Birth (YYYY-MM-DD)` DATE NULL,
  `Telephone Number (###-###-####)` VARCHAR(45) NOT NULL,
  `Does the Client Have an Email Address` VARCHAR(45) NULL,
  `Email Address` VARCHAR(45) NULL,
  `Street Number` VARCHAR(45) NULL,
  `Street Name` VARCHAR(45) NULL,
  `Street Type` VARCHAR(45) NULL,
  `Street Direction` VARCHAR(45) NULL,
  `Unit/Suite/Apt` VARCHAR(45) NULL,
  `City` VARCHAR(45) NULL,
  `Province` VARCHAR(45) NULL,
  `Postal Code` VARCHAR(45) NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Consent for Future Research/Consultation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Unique Identifier`,`Unique Identifier Value`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Needs Assessment&Referrals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Needs Assessment&Referrals` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`Needs Assessment&Referrals` (
  `Processing Details` VARCHAR(250) NULL,
  `Update Record ID` VARCHAR(45) NULL,
  `Unique Identifier` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Postal Code where the service was recieved` VARCHAR(45) NOT NULL,
  `Start Date of Assessment (YYYY-MM-DD)` DATE NOT NULL,
  `Language of Service` VARCHAR(45) NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Type of Institution/Organization Where Client Received Services` VARCHAR(45) NOT NULL,
  `Referred By` VARCHAR(45) NOT NULL,
  `Increase knowledge of: Life in Canada` VARCHAR(45) NULL,
  `Increase knowledge of: Life in Canada Referrals` VARCHAR(45) NULL,
  `Increase knowledge of: Community and Government Services` VARCHAR(45) NULL,
  `Increase knowledge of: Community and Government Services R` VARCHAR(45) NULL,
  `Increase knowledge of: Working in Canada` VARCHAR(45) NULL,
  `Increase knowledge of: Working in Canada Referrals` VARCHAR(45) NULL,
  `Increase knowledge of: Education in Canada` VARCHAR(45) NULL,
  `Increase knowledge of: Education in Canada Referrals` VARCHAR(45) NULL,
  `Increase the following: Social networks` VARCHAR(45) NULL,
  `Increase the following: Social networks Referrals` VARCHAR(45) NULL,
  `Increase the following: Professional networks` VARCHAR(45) NULL,
  `Increase the following: Professional networks Referrals` VARCHAR(45) NULL,
  `Increase the following: Access to local community services` VARCHAR(45) NULL,
  `Increase the following: Access to local community services R` VARCHAR(45) NULL,
  `Increase the following: Level of community involvement` VARCHAR(45) NULL,
  `Increase the following: Level of community involvement R` VARCHAR(45) NULL,
  `Improve Language Skills` VARCHAR(45) NULL,
  `Improve Language Skills Referrals` VARCHAR(45) NULL,
  `Improve Language Skills to` VARCHAR(45) NULL,
  `Improve Other Skills` VARCHAR(45) NULL,
  `Improve Other Skills Referrals` VARCHAR(45) NULL,
  `Improve Other Skills to` VARCHAR(45) NULL,
  `Find employment` VARCHAR(45) NULL,
  `Find employment Referrals` VARCHAR(45) NULL,
  `Find employment: TimeFrame` VARCHAR(45) NULL,
  `Find employment: Minimum one year's work experience?` VARCHAR(45) NULL,
  `Find employment: Intends  Classification skill level?` VARCHAR(45) NULL,
  `Find employment: Intends to have license to work in Canada?` VARCHAR(45) NULL,
  `Client intends to become a Canadian citizen?` VARCHAR(45) NOT NULL,
  `Support services may be required` VARCHAR(45) NOT NULL,
  `Care for Newcomer Children1` VARCHAR(45) NULL,
  `Transportation1` VARCHAR(45) NULL,
  `Provisions for Disability` VARCHAR(45) NULL,
  `Translation Required` VARCHAR(45) NULL,
  `Interpretation Required` VARCHAR(45) NULL,
  `Crisis Counselling1` VARCHAR(45) NULL,
  `Non-IRCC program services needed` VARCHAR(45) NOT NULL,
  `Food/Clothing/Other Material Needs` VARCHAR(45) NULL,
  `NeedsAssessmentAndReferralscol` VARCHAR(45) NULL,
  `Housing/Accommodation` VARCHAR(45) NULL,
  `Housing/Accommodation Referrals` VARCHAR(45) NULL,
  `Health/Mental Health/Well Being` VARCHAR(45) NULL,
  `Health/Mental Health/Well Being Referrals` VARCHAR(45) NULL,
  `Financial` VARCHAR(45) NULL,
  `Financial Referrals` VARCHAR(45) NULL,
  `Family Support` VARCHAR(45) NULL,
  `Family Support Referrals` VARCHAR(45) NULL,
  `Language (Non-IRCC)` VARCHAR(45) NULL,
  `Language (Non-IRCC) Referrals` VARCHAR(45) NULL,
  `Education/Skills Development` VARCHAR(45) NULL,
  `Education/Skills Development Referrals` VARCHAR(45) NULL,
  `Employment-related` VARCHAR(45) NULL,
  `Employment-related Referrals` VARCHAR(45) NULL,
  `Legal Information and Services` VARCHAR(45) NULL,
  `Legal Information and Services Referrals` VARCHAR(45) NULL,
  `Community Services` VARCHAR(45) NULL,
  `Community Services Referrals` VARCHAR(45) NULL,
  `Support Services Received` VARCHAR(45) NULL,
  `Care for Newcomer Children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for Disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `Settlement Plan completed and shared with client` VARCHAR(45) NOT NULL,
  `End Date of Assessment (YYYY-MM-DD)` DATE NOT NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier`, `Unique Identifier Value`),
  CONSTRAINT `fk_NeedsAssessmentAndReferrals_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Employment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Employment` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`Employment` (
  `Processing Details` VARCHAR(250) NULL,
  `Update Record ID` VARCHAR(45) NULL,
  `Unique Identifier` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Postal Code where the service was received` VARCHAR(45) NOT NULL,
  `Registration in an employment intervention` VARCHAR(45) NOT NULL,
  `A referral to` VARCHAR(45) NULL,
  `Language of Service` VARCHAR(45) NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Type of Institution/Organization Where Client Received Services` VARCHAR(45) NOT NULL,
  `Referred By` VARCHAR(45) NOT NULL,
  `Referral Date (YYYY-MM-DD)` DATE NULL,
  `Employment Status` VARCHAR(45) NULL,
  `Education Status` VARCHAR(45) NULL,
  `Occupation in Canada` VARCHAR(100) NULL,
  `Intended Occupation` VARCHAR(100) NULL,
  `Intervention Type` VARCHAR(45) NULL,
  `Long Term Intervention: Intervention Received` VARCHAR(200) NULL,
  `Long Term Intervention: Status of Intervention` VARCHAR(200) NULL,
  `Long Term Intervention: Reason For Leaving Intervention` VARCHAR(200) NULL,
  `Long Term Intervention: Start Date (YYYY-MM-DD)` DATE NULL,
  `Long Term Intervention: End Date (YYYY-MM-DD)` DATE NULL,
  `Long Term Intervention: Size of Employer` VARCHAR(100) NULL,
  `Long Term Intervention: Placement Was` VARCHAR(45) NULL,
  `Long Term Intervention: Hours Per Week` VARCHAR(100) NULL,
  `Long Term Intervention: Client Met Mentor Regularly at` VARCHAR(100) NULL,
  `LTI: Average Hours/Week in Contact with Mentor` VARCHAR(45) NULL,
  `LTI: Profession/Trade For Which Services Were Received` VARCHAR(45) NULL,
  `Skills and Aptitude Training Received as Part of this Service?` VARCHAR(45) NULL,
  `Computer skills` VARCHAR(45) NULL,
  `Document Use` VARCHAR(45) NULL,
  `Interpersonal Skills and Workplace Culture` VARCHAR(45) NULL,
  `Leadership Training` VARCHAR(45) NULL,
  `Numeracy` VARCHAR(45) NULL,
  `Short Term Intervention: Service Received 1` VARCHAR(45) NULL,
  `Short Term Intervention: Date 1 (YYYY-MM-DD)` DATE NULL,
  `Short Term Intervention: Service Received 2` VARCHAR(45) NULL,
  `Short Term Intervention: Date 2 (YYYY-MM-DD)` DATE NULL,
  `Short Term Intervention: Service Received 3` VARCHAR(45) NULL,
  `Short Term Intervention: Date 3 (YYYY-MM-DD)` DATE NULL,
  `Short Term Intervention: Service Received 4` VARCHAR(45) NULL,
  `Short Term Intervention: Date 4 (YYYY-MM-DD)` DATE NULL,
  `Short Term Intervention: Service Received 5` VARCHAR(45) NULL,
  `Short Term Intervention: Date 5 (YYYY-MM-DD)` DATE NULL,
  `Support Services Received` VARCHAR(45) NULL,
  `Care for Newcomer Children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for Disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `Time Spent With Client's Employment Needs: Hours` INT NULL,
  `Time Spent With Client's Employment Needs: Minutes` INT NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier`, `Unique Identifier Value`),
  CONSTRAINT `fk_Employment_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Info&Orien`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Info&Orien` ;
CREATE TABLE IF NOT EXISTS `assignmentdb`.`Info&Orien` (
  `Processing Details` VARCHAR(250) NULL,
  `Update Record ID` VARCHAR(45) NULL,
  `Unique Identifier` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Postal Code where the service was received` VARCHAR(45) NOT NULL,
  `Start Date of Service (YYYY-MM-DD)` DATE NOT NULL,
  `Language of Service` VARCHAR(45) NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Type of Institution/Organization Where Client Received Services` VARCHAR(45) NOT NULL,
  `Referred By` VARCHAR(45) NOT NULL,
  `Services Received` VARCHAR(45) NOT NULL,
  `Total Length of Orientation` VARCHAR(45) NULL,
  `Total Length of Orientation: Hours` INT NULL,
  `Total Length of Orientation: Minutes` INT NULL,
  `Number of Clients in Group` VARCHAR(45) NULL,
  `Directed at a specific Target Group` VARCHAR(45) NULL,
  `Target Group: Children (0-14 yrs)` VARCHAR(45) NULL,
  `Target Group: Youth (15-24 yrs)` VARCHAR(45) NULL,
  `Target Group: Seniors` VARCHAR(45) NULL,
  `Target Group: Gender-specific` VARCHAR(45) NULL,
  `Target Group: Refugees` VARCHAR(45) NULL,
  `Target Group: Ethnic/cultural/linguistic group` VARCHAR(45) NULL,
  `Target Group: Deaf or Hard of Hearing` VARCHAR(45) NULL,
  `Target Group: Blind or Partially Sighted` VARCHAR(45) NULL,
  `TG: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)` VARCHAR(45) NULL,
  `Target Group: Families/Parents` VARCHAR(45) NULL,
  `Target Group: Clients with other impairments (physical, mental)` VARCHAR(45) NULL,
  `TG: Clients w international training in a regulated profession` VARCHAR(45) NULL,
  `TG: Clients with international training in a regulated trade` VARCHAR(45) NULL,
  `Target Group: Official Language minorities` VARCHAR(45) NULL,
  `Overview of Canada` VARCHAR(45) NULL,
  `Overview of Canada Referrals` VARCHAR(45) NULL,
  `Sources of Information` VARCHAR(45) NULL,
  `Sources of Information Referrals` VARCHAR(45) NULL,
  `Rights and Freedoms` VARCHAR(45) NULL,
  `Rights and Freedoms Referrals` VARCHAR(45) NULL,
  `Canadian Law and Justice` VARCHAR(45) NULL,
  `Canadian Law and Justice Referrals` VARCHAR(45) NULL,
  `Important Documents` VARCHAR(45) NULL,
  `Important Documents Referrals` VARCHAR(45) NULL,
  `Improving English or French` VARCHAR(45) NULL,
  `Improving English or French Referrals` VARCHAR(45) NULL,
  `Employment and Income` VARCHAR(45) NULL,
  `Employment and Income Referrals` VARCHAR(45) NULL,
  `Education` VARCHAR(45) NULL,
  `Education Referrals` VARCHAR(45) NULL,
  `Housing` VARCHAR(45) NULL,
  `Housing Referrals` VARCHAR(45) NULL,
  `Health` VARCHAR(45) NULL,
  `Health Referrals` VARCHAR(45) NULL,
  `Money and Finances` VARCHAR(45) NULL,
  `Money and Finances Referrals` VARCHAR(45) NULL,
  `Transportation1` VARCHAR(45) NULL,
  `Transportation Referrals` VARCHAR(45) NULL,
  `Communications and Media` VARCHAR(45) NULL,
  `Communications and Media Referrals` VARCHAR(45) NULL,
  `Community Engagement` VARCHAR(45) NULL,
  `Community Engagement Referrals` VARCHAR(45) NULL,
  `Becoming a Canadian Citizen` VARCHAR(45) NULL,
  `Becoming a Canadian Citizen Referrals` VARCHAR(45) NULL,
  `Interpersonal Conflict` VARCHAR(45) NULL,
  `Interpersonal Conflict Referrals` VARCHAR(45) NULL,
  `Essential Skills and Aptitude Training Received?` VARCHAR(45) NOT NULL,
  `Computer skills` VARCHAR(45) NULL,
  `Document Use` VARCHAR(45) NULL,
  `Interpersonal Skills and Workplace Culture` VARCHAR(45) NULL,
  `Leadership Training` VARCHAR(45) NULL,
  `Numeracy` VARCHAR(45) NULL,
  `Skills or Responsibilities of Citizenship Information Received?` VARCHAR(45) NOT NULL,
  `Life Skills` VARCHAR(45) NULL,
  `Rights and Responsibilities of Citizenship` VARCHAR(45) NULL,
  `Support Services Received` VARCHAR(45) NOT NULL,
  `Care for Newcomer Children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for Disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `End Date of Service (YYYY-MM-DD)` DATE NOT NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier`, `Unique Identifier Value`),
  CONSTRAINT `fk_Info&Orien_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LT Client Enrol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`LT Client Enrol` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`LT Client Enrol` (
  `Processing Details` VARCHAR(250) NULL,
  `Update record ID` VARCHAR(45) NULL,
  `Unique Identifier Type` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Client Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Postal Code where the service was received` VARCHAR(45) NOT NULL,
  `Course Code` VARCHAR(45) NOT NULL,
  `Date of Client's First Class (YYYY-MM-DD)` DATE NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Support services received` VARCHAR(45) NOT NULL,
  `Care for newcomer children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier Type`, `Unique Identifier Value`),
  CONSTRAINT `fk_LT Client Enrol_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier Type`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LT Course Setup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`LT Course Setup` ;


CREATE TABLE IF NOT EXISTS `assignmentdb`.`LT Course Setup` (
  `Processing Details` VARCHAR(250) NULL,
  `Update record ID` VARCHAR(45) NULL,
  `Course Code` VARCHAR(45) NOT NULL,
  `Notes` VARCHAR(45) NULL,
  `Course Held On An Ongoing Basis` Boolean NOT NULL,
  `Official Language of Course` VARCHAR(45) NOT NULL,
  `Format of Training Provided` VARCHAR(100) NOT NULL,
  `Classes Held At` VARCHAR(45) NULL,
  `In-Person Instruction (%)` float NULL,
  `Online/Distance Instruction (%)` float NULL,
  `Total Number of Spots in Course` int NOT NULL,
  `Number of IRCC-Funded Spots in Course` int NOT NULL,
  `New Students Can Enrol in the Course` VARCHAR(45) NOT NULL,
  `Support Services Available for Client in this Course` Boolean NOT NULL,
  `Care for Newcomer Children` Boolean NULL,
  `Transportation` Boolean NULL,
  `Provisions for Disabilities` Boolean NULL,
  `Course Start Date (YYYY-MM-DD)` DATE NOT NULL,
  `Course End Date (YYYY-MM-DD)` DATE NOT NULL,
  `Schedule: Morning` Boolean NULL,
  `Schedule: Afternoon` Boolean NULL,
  `Schedule: Evening` Boolean NULL,
  `Schedule: Weekend` Boolean NULL,
  `Schedule: Anytime` Boolean NULL,
  `Schedule: Online` Boolean NULL,
  `Instructional Hours Per Class` FLOAT NOT NULL,
  `Classes Per Week` INT NOT NULL,
  `Weeks of Instruction` INT NOT NULL,
  `Weeks of Instruction Per Year` INT NULL,
  `Dominant Focus of the Course` VARCHAR(100) NULL,
  `Course Directed at a Specific Target Group` Boolean NULL,
  `Children (0-14 yrs)` Boolean NULL,
  `Youth (15-24 yrs)` Boolean NULL,
  `Senior` Boolean NULL,
  `Gender-specific` Boolean NULL,
  `Refugees` Boolean NULL,
  `Official language minorities` Boolean NULL,
  `Ethnic/cultural/linguistic group` Boolean NULL,
  `Deaf or Hard of Hearing` Boolean NULL,
  `Blind or Partially Sighted` Boolean NULL,
  `Clients with other impairments (physical, mental)` Boolean NULL,
  `Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)` Boolean NULL,
  `Families/Parents` Boolean NULL,
  `Clients with international training in a regulated profession` Boolean NULL,
  `Clients with international training in a regulated trade` Boolean NULL,
  `Materials Used in Course` Boolean NOT NULL,
  `Citizenship preparation` Boolean NULL,
  `PBLA language companion` Boolean NULL,
  `Contact Name` VARCHAR(45) NULL,
  `Street Number` int NOT NULL,
  `Street Name` VARCHAR(45) NOT NULL,
  `Street Type` VARCHAR(45) NOT NULL,
  `Street Direction` VARCHAR(45) NULL,
  `Unit/Suite` int NULL,
  `Province` VARCHAR(45) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Postal Code (A#A#A#)` VARCHAR(45) NOT NULL,
  `Telephone Number (###-###-####)` VARCHAR(45) NOT NULL,
  `Telephone Extension` VARCHAR(45) NULL,
  `Email Address` VARCHAR(45) NOT NULL,
  `Listening Skill Level 1` varchar(45) NULL,
  `Listening Skill Level 2` varchar(45) NULL,
  `Listening Skill Level 3` varchar(45) NULL,
  `Listening Skill Level 4` varchar(45) NULL,
  `Listening Skill Level 5` varchar(45) NULL,
  `Listening Skill Level 6` varchar(45) NULL,
  `Listening Skill Level 7` varchar(45) NULL,
  `Listening Skill Level 8` varchar(45) NULL,
  `Listening Skill Level 9` varchar(45) NULL,
  `Listening Skill Level 10` varchar(45) NULL,
  `Listening Skill Level 11` varchar(45) NULL,
  `Listening Skill Level 12` varchar(45) NULL,
  `Speaking Skill Level 1` varchar(45) NULL,
  `Speaking Skill Level 2` varchar(45) NULL,
  `Speaking Skill Level 3` varchar(45) NULL,
  `Speaking Skill Level 4` varchar(45) NULL,
  `Speaking Skill Level 5` varchar(45) NULL,
  `Speaking Skill Level 6` varchar(45) NULL,
  `Speaking Skill Level 7` varchar(45) NULL,
  `Speaking Skill Level 8` varchar(45) NULL,
  `Speaking Skill Level 9` varchar(45) NULL,
  `Speaking Skill Level 10` varchar(45) NULL,
  `Speaking Skill Level 11` varchar(45) NULL,
  `Speaking Skill Level 12` varchar(45) NULL,
  `Reading Skill Level 1` varchar(45) NULL,
  `Reading Skill Level 2` varchar(45) NULL,
  `Reading Skill Level 3` varchar(45) NULL,
  `Reading Skill Level 4` varchar(45) NULL,
  `Reading Skill Level 5` varchar(45) NULL,
  `Reading Skill Level 6` varchar(45) NULL,
  `Reading Skill Level 7` varchar(45) NULL,
  `Reading Skill Level 8` varchar(45) NULL,
  `Reading Skill Level 9` varchar(45) NULL,
  `Reading Skill Level 10` varchar(45) NULL,
  `Reading Skill Level 11` varchar(45) NULL,
  `Reading Skill Level 12` varchar(45) NULL,
  `Reading Skill Level 13` varchar(45) NULL,
  `Reading Skill Level 14` varchar(45) NULL,
  `Reading Skill Level 15` varchar(45) NULL,
  `Reading Skill Level 16` varchar(45) NULL,
  `Reading Skill Level 17` varchar(45) NULL,
  `Writing Skill Level 1` varchar(45) NULL,
  `Writing Skill Level 2` varchar(45) NULL,
  `Writing Skill Level 3` varchar(45) NULL,
  `Writing Skill Level 4` varchar(45) NULL,
  `Writing Skill Level 5` varchar(45) NULL,
  `Writing Skill Level 6` varchar(45) NULL,
  `Writing Skill Level 7` varchar(45) NULL,
  `Writing Skill Level 8` varchar(45) NULL,
  `Writing Skill Level 9` varchar(45) NULL,
  `Writing Skill Level 10` varchar(45) NULL,
  `Writing Skill Level 11` varchar(45) NULL,
  `Writing Skill Level 12` varchar(45) NULL,
  `Writing Skill Level 13` varchar(45) NULL,
  `Writing Skill Level 14` varchar(45) NULL,
  `Writing Skill Level 15` varchar(45) NULL,
  `Writing Skill Level 16` varchar(45) NULL,
  `Writing Skill Level 17` varchar(45) NULL,
  PRIMARY KEY (`Course Code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Community Connections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`Community Connections` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`Community Connections` (
  `Processing Details` VARCHAR(250) NULL,
  `Update Record ID` VARCHAR(45) NULL,
  `Unique Identifier` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Postal Code where the service was received` VARCHAR(45) NOT NULL,
  `Language of Service` VARCHAR(45) NOT NULL,
  `Official Language of Preference` VARCHAR(45) NOT NULL,
  `Referred By` VARCHAR(45) NOT NULL,
  `Activity Under Which Client Received Services` VARCHAR(45) NOT NULL,
  `Type of Institution/Organization Where Client Received Services` VARCHAR(45) NOT NULL,
  `Type of Event Attended` VARCHAR(100) NULL,
  `Type of Service` VARCHAR(100) NULL,
  `Main Topic/Focus of the Service Received` VARCHAR(100) NOT NULL,
  `Service Received` VARCHAR(100) NOT NULL,
  `Number of Unique Participants` VARCHAR(45) NULL,
  `Did Volunteers from the Host Community Participate?` VARCHAR(45) NULL,
  `Directed at a Specific Target Group` VARCHAR(45) NULL,
  `Target Group: Children (0-14 yrs)` VARCHAR(45) NULL,
  `Target Group: Youth (15-24 yrs)` VARCHAR(45) NULL,
  `Target Group: Senior` VARCHAR(45) NULL,
  `Target Group: Gender-specific` VARCHAR(45) NULL,
  `Target Group: Refugees` VARCHAR(45) NULL,
  `Target Group: Ethnic/cultural/linguistic group` VARCHAR(45) NULL,
  `Target Group: Deaf or Hard of Hearing` VARCHAR(45) NULL,
  `Target Group: Blind or Partially Sighted` VARCHAR(45) NULL,
  `Target Group: Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)` VARCHAR(45) NULL,
  `Target Group: Families/Parents` VARCHAR(45) NULL,
  `Target Group: Other impairments (physical, mental)` VARCHAR(45) NULL,
  `TG: Clients w international training in a regulated profession` VARCHAR(45) NULL,
  `TG: Clients with international training in a regulated trade` VARCHAR(45) NULL,
  `Target Group: Official language minorities` VARCHAR(45) NULL,
  `Status of Service` VARCHAR(100) NOT NULL,
  `Reason for Leaving Service` VARCHAR(200) NULL,
  `Start Date (YYYY-MM-DD)` DATE NOT NULL,
  `End Date (YYYY-MM-DD)` DATE NULL,
  `Projected End Date (YYYY-MM-DD)` DATE NULL,
  `Essential Skills and Aptitudes Training Received?` VARCHAR(45) NOT NULL,
  `Computer Skills` VARCHAR(45) NULL,
  `Document Use` VARCHAR(45) NULL,
  `Interpersonal Skills and Workplace Culture` VARCHAR(45) NULL,
  `Leadership Training` VARCHAR(45) NULL,
  `Life Skills` VARCHAR(45) NULL,
  `Numeracy` VARCHAR(45) NULL,
  `Support Services Received` VARCHAR(45) NOT NULL,
  `Care for Newcomer Children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for Disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `Total Length of Service: Hours` INT NULL,
  `Total Length of Service: Minutes` INT NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier`, `Unique Identifier Value`),
  CONSTRAINT `fk_Community Connections_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LT Client Exit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `assignmentdb`.`LT Client Exit` ;

CREATE TABLE IF NOT EXISTS `assignmentdb`.`LT Client Exit` (
  `Processing Details` VARCHAR(250) NULL,
  `Update record ID` VARCHAR(45) NULL,
  `Unique Identifier Type` VARCHAR(45) NOT NULL,
  `Unique Identifier Value` VARCHAR(45) NOT NULL,
  `Client Date of Birth (YYYY-MM-DD)` DATE NOT NULL,
  `Course Code` VARCHAR(45) NOT NULL,
  `Client's Training Status` VARCHAR(45) NOT NULL,
  `Date Client Exited Course (YYYY-MM-DD)` DATE NULL,
  `Reason for Exiting course` VARCHAR(200) NULL,
  `Listening CLB Level` VARCHAR(45) NULL,
  `Speaking CLB Level` VARCHAR(45) NULL,
  `Reading CLB Level` VARCHAR(45) NULL,
  `Writing CLB Level` VARCHAR(45) NULL,
  `Was a Certificate issued to the client?` VARCHAR(45) NOT NULL,
  `Listening level indicated on Certificate` VARCHAR(45) NULL,
  `Speaking level indicated on Certificate` VARCHAR(45) NULL,
  `Support services received` VARCHAR(45) NOT NULL,
  `Care for newcomer children` VARCHAR(45) NULL,
  `Child 1: Age` VARCHAR(45) NULL,
  `Child 1: Type of Care` VARCHAR(45) NULL,
  `Child 2: Age` VARCHAR(45) NULL,
  `Child 2: Type of Care` VARCHAR(45) NULL,
  `Child 3: Age` VARCHAR(45) NULL,
  `Child 3: Type of Care` VARCHAR(45) NULL,
  `Child 4: Age` VARCHAR(45) NULL,
  `Child 4: Type of Care` VARCHAR(45) NULL,
  `Child 5: Age` VARCHAR(45) NULL,
  `Child 5: Type of Care` VARCHAR(45) NULL,
  `Transportation` VARCHAR(45) NULL,
  `Provisions for disabilities` VARCHAR(45) NULL,
  `Translation` VARCHAR(45) NULL,
  `Translation language Between` VARCHAR(45) NULL,
  `Translation language And` VARCHAR(45) NULL,
  `Interpretation` VARCHAR(45) NULL,
  `Interpretation language Between` VARCHAR(45) NULL,
  `Interpretation language And` VARCHAR(45) NULL,
  `Crisis Counselling` VARCHAR(45) NULL,
  `Reason for update` VARCHAR(200) NULL,
  PRIMARY KEY (`Unique Identifier Type`, `Unique Identifier Value`),
  CONSTRAINT `fk_LT Client Exit_has_ClientProfile1`
    FOREIGN KEY (`Unique Identifier Type`,`Unique Identifier Value`)
    REFERENCES `Client Profile` (`Unique Identifier`,`Unique Identifier Value`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `roleID` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'utsc'),(2,'teqlip'),(3,'org');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_login` (
  `userID` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `fk_user_login_users1` FOREIGN KEY (`userID`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (1,'pat',1),(2,'ethan',1),(3,'harmony',1),(4,'amy',0);
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_password`
--

DROP TABLE IF EXISTS `user_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_password` (
  `userID` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `fk_user_password_user_login` FOREIGN KEY (`userID`) REFERENCES `user_login` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_password`
--

LOCK TABLES `user_password` WRITE;
/*!40000 ALTER TABLE `user_password` DISABLE KEYS */;
INSERT INTO `user_password` VALUES (1,'97982a5b1414b9078103a1c008c4e3526c27b41cdbcf80790560a40f2a9bf2ed4427ab1428789915ed4b3dc07c454bd9'),(2,'3b903eebdb4b25efd0e911028181e87edf4234e64088fd60583f74fd2bf46ba17993484bee6cd451f296de279c42393e'),(3,'504f008c8fcf8b2ed5dfcde752fc5464ab8ba064215d9c5b5fc486af3d9ab8c81b14785180d2ad7cee1ab792ad44798c'),(4,'a8b64babd0aca91a59bdbb7761b421d4f2bb38280d3a75ba0f21f2bebc45583d446c598660c94ce680c47d19c30783a7');
/*!40000 ALTER TABLE `user_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `roleID` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phoneNumber` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_users_roles1_idx` (`roleID`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`roleID`) REFERENCES `roles` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `queries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `queries` (
  `queryID` int(5) NOT NULL AUTO_INCREMENT,
  `query` varchar(200) NOT NULL UNIQUE,
  PRIMARY KEY (`queryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Pat','Meheiny',NULL,1,'pat@utsc.ca',NULL),(2,'Ethan','Hershey',NULL,2,'ethan.h@teqlip.ca','416-234-9876'),(3,'Harmony','Davis',NULL,2,'harmony@teqlip.ca',NULL),(4,'Amy','Ayurel',NULL,3,'amyamy@someorg.ca','234-123-4321');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-16 12:35:51
