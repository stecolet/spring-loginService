CREATE TABLE User (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL DEFAULT '',
  password varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
commit;