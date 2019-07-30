CREATE TABLE `user` (
  `id` int(25) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(32) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


INSERT INTO `test`.`user`(`id`, `name`, `age`, `sex`, `password`) VALUES (2, '小白', 1, '0', '333');
INSERT INTO `test`.`user`(`id`, `name`, `age`, `sex`, `password`) VALUES (3, 'white', 12, '0', '111');
INSERT INTO `test`.`user`(`id`, `name`, `age`, `sex`, `password`) VALUES (4, 'white', 4, '0', '222');

