create table `tb_iplocation` (
	`longitude` varchar (20),
	`latitude` varchar (20),
	`total_count` bigint (20)
);



CREATE TABLE `tb_iplocation` (
  `longitude` varchar(20) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `total_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`longitude`,`latitude`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
