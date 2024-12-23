CREATE TABLE IF NOT EXISTS `webdb`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `contents` TEXT NOT NULL,
  `hit` INT NOT NULL,
  `reg_date` DATETIME NOT NULL,
  `g_no` INT NOT NULL,
  `o_no` INT NOT NULL,
  `depth` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_board_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `webdb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

desc board;

select * from board;
select * from user;
insert into board values(null, "모먹지?", "글 내용",5, "2024-07-15",1,1,0,9);
insert into board values(null, "ㅎㅇ!", "글 내용",30, "2024-07-14",2,1,0,9);
insert into board values(null, "짬뽕", "글 내용",23, "2024-07-16",1,4,1,10);
insert into board values(null, "무아국수", "글 내용",33, "2024-07-16",1,2,1,10);
insert into board values(null, "놉!", "글 내용",74, "2024-07-17",1,3,2,9);

delete from board;