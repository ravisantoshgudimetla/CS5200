 CREATE TABLE `test`.`actor` (
  `id` INT NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `dateofBirth` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  CREATE TABLE `test`.`movie` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `posterImage` VARCHAR(45) NULL,
  `releaseDate` VARCHAR(45) NULL)
  
  CREATE TABLE `test`.`comment` (
  `id` INT IDENTITY (1, 1) NOT NULL,
  `comment` VARCHAR(45) NULL,
  `date` DATE NULL
  `username` INT NOT NULL
  FOREIGN KEY (`username`) references User (`username`)
 );
 CREATE TABLE `test`.`user`(
  username VARCHAR (50) NULL,
  firstName VARCHAR (50) NULL,
  lastName VARCHAR (50) NULL,
  email VARCHAR (50) NULL,
  dateOfBirth VARCHAR (50) NULL,
  PRIMARY KEY(`username`)
 ); 
 
 
 CREATE TABLE `test`.`cast`(
  Id INT IDENTITY (1, 1) NOT NULL,
  characterName VARCHAR (50) NOT NULL,
  actorId INT,
  movieId INT,
  FOREIGN KEY `actorId` references actor (`id`)
  FOREIGN KEY `movieId` references movie (`id`)
 );
 
 Data Choices:
 I have put the data type for date and poster image as strings for simplicity of access in the front end.
 I also added columns in cast table to capture the relationship between cast and actor/movie.
 Added username column for Comment table so that there could be some relationship between comment and user who
 made it.
 