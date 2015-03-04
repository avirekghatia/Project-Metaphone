-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2014 at 05:15 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_proj_1`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `signup`(
    in username int(15),
    in fname varchar(20),
    in lname varchar(20),
    in DOB date,
    in city varchar(10),    
    in emailID varchar(30)
    )
begin
	if  not exists (select emailID from user where emailID = @e) then
		insert into user values (username, fname, lname, DOB, city, emailID);
    
    end if;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `signup3`(
    in username int(15),
    in fname varchar(20),
    in lname varchar(20),
    in DOB date,
    in city varchar(10),    
    in emailID varchar(30)
    )
begin
	if  not exists (select emailID from user where email = @emailID) then
		insert into user values (username, fname, lname, DOB, city, emailID);
    
    end if;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `test2`()
begin

select ‘Hello World’;

end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `attend`
--

CREATE TABLE IF NOT EXISTS `attend` (
  `concertID` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `rate` int(1) DEFAULT NULL,
  `dateTime` timestamp NOT NULL,
  `review` text,
  PRIMARY KEY (`concertID`,`username`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attend`
--

INSERT INTO `attend` (`concertID`, `username`, `rate`, `dateTime`, `review`) VALUES
('CONC001', 'MukulHodar', 4, '2014-11-23 05:00:00', 'Very good concert, loved the type of music! Awesome!!'),
('CONC003', 'sagu', 3, '2014-12-04 21:56:29', '<p>Very good concert, lively<br></p>');

-- --------------------------------------------------------

--
-- Table structure for table `band`
--

CREATE TABLE IF NOT EXISTS `band` (
  `BandID` varchar(10) NOT NULL,
  `Password` varchar(8) NOT NULL,
  `BandName` varchar(20) NOT NULL,
  `Info` text,
  `city` varchar(20) NOT NULL,
  `webpage` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BandID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `band`
--

INSERT INTO `band` (`BandID`, `Password`, `BandName`, `Info`, `city`, `webpage`) VALUES
('BND001', 'BND001', 'New York Classic', 'Established in 1940. more than 2000 shows so far.', 'New York', 'http://www.NewYorkClassic.com'),
('BND002', 'BND002', 'Dallas Classic', 'Established in 1940. more than 2000 shows so far.', 'New York', 'http://www.DallasClassic.com'),
('BND003', 'BND003', 'Boston Classic', 'Established in 1940. more than 2000 shows so far.', 'New York', 'http://www.BostonClassic.com'),
('BND004', 'BND004', 'Jersy Classic', 'Established in 1940. more than 2000 shows so far.', 'New York', 'http://www.JersyClassic.com'),
('BND005', 'BND005', 'San Jose Classic', 'Established in 1940. more than 2000 shows so far.', 'New York', 'http://www.SanJoseClassic.com');

-- --------------------------------------------------------

--
-- Table structure for table `band_music_taste`
--

CREATE TABLE IF NOT EXISTS `band_music_taste` (
  `bandID` varchar(10) NOT NULL,
  `music_name` varchar(10) NOT NULL,
  PRIMARY KEY (`bandID`,`music_name`),
  KEY `music_name` (`music_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `band_music_taste`
--

INSERT INTO `band_music_taste` (`bandID`, `music_name`) VALUES
('BND001', 'BebobJazz'),
('BND001', 'Blues'),
('BND002', 'Blues'),
('BND005', 'Blues'),
('BND001', 'FreeJazz'),
('BND003', 'Jazz'),
('BND004', 'NJ Hip Hop');

-- --------------------------------------------------------

--
-- Table structure for table `concert`
--

CREATE TABLE IF NOT EXISTS `concert` (
  `ConcertID` varchar(10) NOT NULL,
  `concertName` varchar(40) NOT NULL,
  `BandID` varchar(10) NOT NULL,
  `venueID` varchar(10) NOT NULL,
  `datetime` datetime NOT NULL,
  `ticketLink` text,
  `TicketPrice` decimal(5,0) DEFAULT NULL,
  `seatsAvailable` int(5) NOT NULL,
  PRIMARY KEY (`ConcertID`),
  KEY `venueID` (`venueID`),
  KEY `BandID` (`BandID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `concert`
--

INSERT INTO `concert` (`ConcertID`, `concertName`, `BandID`, `venueID`, `datetime`, `ticketLink`, `TicketPrice`, `seatsAvailable`) VALUES
('CONC001', 'Classics G', 'BND001', 'VENUE001', '2014-11-10 16:30:00', NULL, '50', 200),
('CONC002', 'Boston Classic Grand', 'BND003', 'VENUE005', '2014-11-30 16:30:00', NULL, '50', 200),
('CONC003', 'Jersy  Grand', 'BND004', 'VENUE002', '2014-11-25 16:30:00', NULL, '50', 200),
('CONC004', 'San Grand', 'BND005', 'VENUE003', '2014-12-10 16:30:00', NULL, '50', 200),
('CONC005', 'Dallas  Grand', 'BND002', 'VENUE004', '2014-11-29 16:30:00', NULL, '50', 200);

-- --------------------------------------------------------

--
-- Table structure for table `fan`
--

CREATE TABLE IF NOT EXISTS `fan` (
  `bandId` varchar(10) NOT NULL,
  `Username` varchar(10) NOT NULL,
  PRIMARY KEY (`bandId`,`Username`),
  KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fan`
--

INSERT INTO `fan` (`bandId`, `Username`) VALUES
('BND001', 'HRU001'),
('BND002', 'HRU001'),
('BND001', 'MUH001'),
('BND002', 'MUH001'),
('BND003', 'MUH001'),
('BND004', 'MUH001'),
('BND001', 'mukulhodar'),
('BND002', 'MukulHodar'),
('BND001', 'neerajdorl'),
('BND001', 'SRK001'),
('BND002', 'SRK001'),
('BND003', 'SRK001'),
('BND004', 'SRK001');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE IF NOT EXISTS `follow` (
  `follower` varchar(10) NOT NULL,
  `following` varchar(10) NOT NULL,
  `dateTime` datetime NOT NULL,
  PRIMARY KEY (`follower`,`following`),
  KEY `following` (`following`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`follower`, `following`, `dateTime`) VALUES
('MUH001', 'ANI001', '2014-11-05 00:00:00'),
('MUH001', 'HRU001', '2014-11-05 00:00:00'),
('MUH001', 'KET001', '2014-11-05 00:00:00'),
('MUH001', 'PRA001', '2014-11-05 00:00:00'),
('MUH001', 'SAG001', '2014-11-05 00:00:00'),
('MUH001', 'SAN001', '2014-11-05 00:00:00'),
('MUH001', 'SRK001', '2014-11-05 00:00:00'),
('mukulhodar', 'MukulHodar', '2014-12-04 14:17:24'),
('neerajdorl', 'MUH001', '2014-12-04 01:58:13'),
('neerajdorl', 'MukulHodar', '2014-12-04 01:53:35'),
('sagu', 'HRU001', '2014-12-04 16:35:41'),
('SRK001', 'ANI001', '2014-11-05 00:00:00'),
('SRK001', 'HRU001', '2014-11-05 00:00:00'),
('SRK001', 'KET001', '2014-11-05 00:00:00'),
('SRK001', 'PRA001', '2014-11-05 00:00:00'),
('SRK001', 'SAG001', '2014-11-05 00:00:00'),
('SRK001', 'SAN001', '2014-11-05 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `music`
--

CREATE TABLE IF NOT EXISTS `music` (
  `musicName` varchar(20) NOT NULL DEFAULT '',
  `parentMusic` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`musicName`),
  KEY `parentMusic` (`parentMusic`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `music`
--

INSERT INTO `music` (`musicName`, `parentMusic`) VALUES
('Blues', NULL),
('Hip Hop', NULL),
('Jazz', NULL),
('Alternative Hip Hop', 'Hip Hop'),
('NJ Hip Hop', 'Hip Hop'),
('Southern Hip Hop', 'Hip Hop'),
('BebobJazz', 'Jazz'),
('CoolJazz', 'Jazz'),
('FreeJazz', 'Jazz');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `postID` varchar(10) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `BandID` varchar(10) DEFAULT NULL,
  `dateTimeStamp` timestamp NOT NULL,
  `Content` text NOT NULL,
  PRIMARY KEY (`postID`),
  KEY `username` (`username`,`BandID`),
  KEY `BandID` (`BandID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postID`, `username`, `BandID`, `dateTimeStamp`, `Content`) VALUES
('POST000002', NULL, 'BND001', '2014-11-24 03:21:41', 'Hello New Yorkers!!.\r\nwe performing live at Barclay''s..\r\n\r\nStay tuned for more information..!!!'),
('POST00001', 'MUH001', NULL, '2014-11-24 03:20:40', 'There is an awesome concert coming soon by New York Classic, Must watch!!..');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `city` varchar(10) NOT NULL,
  `emailID` varchar(30) NOT NULL,
  `regDate` datetime NOT NULL,
  `LastLogInTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Password`, `fName`, `lName`, `dob`, `city`, `emailID`, `regDate`, `LastLogInTime`) VALUES
('ANI001', 'ANI001', 'ANI', 'Will', '1989-05-07', 'Dallas', 'ANI001@some.com', '2014-11-01 00:00:00', NULL),
('HRU001', 'HRU001', 'Hrushikesh', 'Kalburgi', '1989-11-19', 'New York', 'hru001@some.com', '2013-10-01 00:00:00', NULL),
('JYO001', 'JYO001', 'JYO', 'Jain', '1989-02-08', 'Dallas', 'JYO001@some.com', '2014-11-01 00:00:00', NULL),
('KAS001', 'KAS001', 'KAS', 'Naco', '1989-03-19', 'Boston', 'KAS001@some.com', '2014-11-01 00:00:00', NULL),
('KET001', 'KET001', 'KET', 'Ghot', '1989-12-19', 'San Jose', 'KET001@some.com', '2014-11-01 00:00:00', NULL),
('MUH001', 'MUH001', 'Muk', 'Hoda', '1989-10-19', 'New York', 'MUH001@some.com', '2014-11-01 00:00:00', NULL),
('muks', '', 'mu', 'ks', '2014-12-12', 'NYC', 'muks@c.com', '2014-12-01 00:00:00', NULL),
('muks2', 'muksw', '', '', '0000-00-00', '', '', '2014-12-01 00:00:00', NULL),
('MukulHodar', 'mukul123', 'mukul', 'hodarkar', '0000-00-00', 'Buffalo', 'muh205@nyu.edu', '2014-11-01 00:00:00', '2014-11-24 00:00:00'),
('neerajdorl', 'NEERAJDORL', 'NEERAJ', 'DORLE', '1991-12-27', 'pune', 'neerajdorle@gmail.com', '2014-12-03 00:00:00', NULL),
('orio', 'orio123', 'or', 'io', '2012-12-12', 'Buffalo', 'orio@nyu.edu', '2014-12-01 00:00:00', NULL),
('PRA001', 'PRA001', 'PRA', 'Raja', '1989-01-19', 'San Jose', 'PRA001@some.com', '2014-11-01 00:00:00', NULL),
('RAV001', 'RAV001', 'RAV', 'Math', '1989-01-10', 'Boston', 'RAV001@some.com', '2014-11-01 00:00:00', NULL),
('SAG001', 'SAG001', 'SAG', 'Pati', '1989-04-19', 'San Jose', 'SAG001@some.com', '2014-11-01 00:00:00', NULL),
('sagu', 'sagu', 'sagar', 'dada', '1991-05-21', 'pune', 'sagar@gmail.com', '2014-12-04 00:00:00', NULL),
('SAN001', 'SAN001', 'SAN', 'Ingu', '1989-12-19', 'Jersy City', 'SAN001@some.com', '2014-11-01 00:00:00', NULL),
('SRK001', 'SRK001', 'SRK', 'Kale', '1989-11-19', 'New York', 'SRK001@some.com', '2014-11-01 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `userlist`
--

CREATE TABLE IF NOT EXISTS `userlist` (
  `username` varchar(10) NOT NULL,
  `ConcertID` varchar(10) NOT NULL,
  PRIMARY KEY (`username`,`ConcertID`),
  KEY `ConcertID` (`ConcertID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlist`
--

INSERT INTO `userlist` (`username`, `ConcertID`) VALUES
('ANI001', 'CONC001'),
('KET001', 'CONC001'),
('MUH001', 'CONC001'),
('PRA001', 'CONC001'),
('SAG001', 'CONC001'),
('SAN001', 'CONC001'),
('SRK001', 'CONC001'),
('ANI001', 'CONC002'),
('HRU001', 'CONC002'),
('MUH001', 'CONC002'),
('MUH001', 'CONC003'),
('SRK001', 'CONC003'),
('MUH001', 'CONC005');

-- --------------------------------------------------------

--
-- Table structure for table `user_music_taste`
--

CREATE TABLE IF NOT EXISTS `user_music_taste` (
  `username` varchar(10) NOT NULL,
  `music_name` varchar(20) NOT NULL,
  PRIMARY KEY (`username`,`music_name`),
  KEY `music_name` (`music_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_music_taste`
--

INSERT INTO `user_music_taste` (`username`, `music_name`) VALUES
('HRU001', 'Alternative Hip Hop'),
('MUH001', 'Alternative Hip Hop'),
('MukulHodar', 'Alternative Hip Hop'),
('KET001', 'Blues'),
('MUH001', 'Blues'),
('MukulHodar', 'Blues'),
('RAV001', 'Blues'),
('SRK001', 'Blues'),
('MUH001', 'CoolJazz'),
('MukulHodar', 'FreeJazz'),
('neerajdorl', 'FreeJazz'),
('SRK001', 'FreeJazz'),
('JYO001', 'Hip Hop'),
('SAN001', 'Hip Hop'),
('MUH001', 'Jazz'),
('PRA001', 'Jazz'),
('SRK001', 'Jazz'),
('KAS001', 'NJ Hip Hop');

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE IF NOT EXISTS `venue` (
  `venueID` varchar(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `City` varchar(10) NOT NULL,
  `Capacity` int(5) NOT NULL,
  `ticketLink` varchar(50) NOT NULL,
  PRIMARY KEY (`venueID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`venueID`, `Name`, `City`, `Capacity`, `ticketLink`) VALUES
('VENUE001', 'Barclay''s ', 'New York', 5000, 'httt://www.tickets.barklays.com'),
('VENUE002', 'City Hall ', 'Jersy City', 8000, 'httt://www.tickets.cityhall.com'),
('VENUE003', 'Adam''s Hall', 'San Jose', 500, 'httt://www.tickets.adams.com'),
('VENUE004', 'marriot', 'dallas', 40, 'httt://www.tickets.marriot.dallas.com'),
('VENUE005', 'Bay Ring ', 'Boston', 5000, 'httt://www.tickets.bayring.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attend`
--
ALTER TABLE `attend`
  ADD CONSTRAINT `attend_ibfk_1` FOREIGN KEY (`concertID`) REFERENCES `concert` (`ConcertID`),
  ADD CONSTRAINT `attend_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `band_music_taste`
--
ALTER TABLE `band_music_taste`
  ADD CONSTRAINT `band_music_taste_ibfk_1` FOREIGN KEY (`bandID`) REFERENCES `band` (`BandID`),
  ADD CONSTRAINT `band_music_taste_ibfk_2` FOREIGN KEY (`music_name`) REFERENCES `music` (`musicName`);

--
-- Constraints for table `concert`
--
ALTER TABLE `concert`
  ADD CONSTRAINT `concert_ibfk_1` FOREIGN KEY (`venueID`) REFERENCES `venue` (`venueID`),
  ADD CONSTRAINT `concert_ibfk_2` FOREIGN KEY (`BandID`) REFERENCES `band` (`BandID`);

--
-- Constraints for table `fan`
--
ALTER TABLE `fan`
  ADD CONSTRAINT `fan_ibfk_1` FOREIGN KEY (`bandId`) REFERENCES `band` (`BandID`),
  ADD CONSTRAINT `fan_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`);

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`follower`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`following`) REFERENCES `user` (`Username`);

--
-- Constraints for table `music`
--
ALTER TABLE `music`
  ADD CONSTRAINT `music_ibfk_1` FOREIGN KEY (`parentMusic`) REFERENCES `music` (`musicName`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`BandID`) REFERENCES `band` (`BandID`);

--
-- Constraints for table `userlist`
--
ALTER TABLE `userlist`
  ADD CONSTRAINT `userlist_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `userlist_ibfk_2` FOREIGN KEY (`ConcertID`) REFERENCES `concert` (`ConcertID`);

--
-- Constraints for table `user_music_taste`
--
ALTER TABLE `user_music_taste`
  ADD CONSTRAINT `user_music_taste_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `user_music_taste_ibfk_2` FOREIGN KEY (`music_name`) REFERENCES `music` (`musicName`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
