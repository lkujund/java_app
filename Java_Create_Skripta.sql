use master
go

create database JavaMovies
go

use JavaMovies
go

create table Director
(
IdDirector int primary key IDENTITY,
Name nvarchar (100),
Surname nvarchar(100)
)
go

create table MovieActor
(
Id int primary key IDENTITY,
MovieId int not null,
ActorId int not null
)
go

create table MovieDirector
(
Id int primary key IDENTITY,
MovieId int not null,
DirectorId int not null
)
go

create table MovieGenre
(
Id int primary key IDENTITY,
MovieId int not null,
GenreId int not null
)
go

create table Actor
(
IdActor int primary key IDENTITY,
[Name] nvarchar (100),
Surname nvarchar(100)
)
go

create table Genre
(
IdGenre int primary key IDENTITY,
[Name] nvarchar(100)
)
go

create table Movie
(
IdMovie int primary key IDENTITY,
Title nvarchar(200),
[Description] nvarchar(1000),
OriginalTitle nvarchar(200),
DurationMinutes int not null,
[Year] int,
ImageUrl nvarchar(max)
)
go

create table [User]
(
IdUser int primary key IDENTITY,
Username nvarchar(50) not null,
Password nvarchar (50) not null,
AdminRole bit not null,

)
ALTER TABLE [User]
ADD constraint UQ_Username UNIQUE(Username)
go



-------------------------------------------
--DIRECTOR

create or alter proc CreateDirector
@Name nvarchar(100),
@Surname nvarchar(100),
@IdDirector int OUTPUT
as
begin
	insert into Director([Name], Surname) 
	values (@Name, @Surname)
	SET @IdDirector = SCOPE_IDENTITY()
end
go

create or alter proc SelectDirectors
as
begin 
	select * from Director
end
go

create or alter proc SelectDirector
@IdDirector int
as
begin 
	select * from Director where IdDirector = @IdDirector
end
go

create or alter proc UpdateDirector
@IdDirector int,
@Name nvarchar(100),
@Surname nvarchar(100)
as
begin 
	update Director
	set [Name] = @Name,
	Surname = @Surname
	where IdDirector = @IdDirector
end
go

create or alter proc DeleteDirector
@IdDirector int
as
begin 
	delete from Director where IdDirector = @IdDirector
end
go

------------------------------------------
--MOVIE ACTOR

create or alter proc AddActorToMovie
@Title nvarchar(200),
@Name nvarchar(100),
@Surname nvarchar(100)
as
begin
	insert into MovieActor (MovieId, ActorId) values
	((select top 1 IdMovie from Movie where Title = @Title),
	(select top 1 IdActor from Actor where [Name] = @Name and Surname = @Surname))
end
go

create or alter proc GetActorsForMovie
@IdMovie int
as
begin
	select * from Actor 
	where IdActor in
	(select ActorId from MovieActor where MovieId = @IdMovie)
end
go
------------------------------------------
--MOVIE GENRE

create or alter proc AddGenreToMovie
@Title nvarchar(200),
@Name nvarchar(100)
as
begin
	insert into MovieGenre(MovieId, GenreId) values
	((select top 1 IdMovie from Movie where Title = @Title),
	(select top 1 IdGenre from Genre where [Name] = @Name))
end
go

create or alter proc GetGenresForMovie
@IdMovie int
as
begin
	select * from Genre 
	where [IdGenre] in
	(select GenreId from MovieGenre where MovieId = @IdMovie)
end
go

------------------------------------------
--MOVIE DIRECTOR

create or alter proc AddDirectorToMovie
@Title nvarchar(200),
@Name nvarchar(100),
@Surname nvarchar(100)
as
begin
	insert into MovieDirector(MovieId, DirectorId) values
	((select top 1 IdMovie from Movie where Title = @Title),
	(select top 1 IdDirector from Director where [Name] = @Name and Surname = @Surname))
end
go

create or alter proc GetDirectorsForMovie
@IdMovie int
as
begin
	select * from Director 
	where IdDirector in
	(select DirectorId from MovieDirector where MovieId = @IdMovie)
end
go
------------------------------------------
--ACTOR

create or alter proc CreateActor
@Name nvarchar(100),
@Surname nvarchar(100),
@IdActor int OUTPUT
as
begin
	insert into Actor values (@Name, @Surname)
	SET @IdActor = SCOPE_IDENTITY()
end
go

create or alter proc SelectActor
@IdActor int
as
begin
	select * from Actor where IdActor = @IdActor
end
go

create or alter proc SelectActors
as
begin
	select * from Actor
end
go

create or alter proc UpdateActor
@IdActor int,
@Name nvarchar(100),
@Surname nvarchar(100)
as
begin
	update Actor
	set [Name] = @Name,
	Surname = @Surname
	where IdActor = @IdActor
end
go

create or alter proc DeleteActor
@IdActor int
as 
begin
	delete from MovieActor where ActorId = @IdActor
	delete from Actor where IdActor = @IdActor
end
go

------------------------------------------
--MOVIE

create or alter proc CreateMovie
@Title nvarchar(200),
@Description nvarchar(1000),
@OriginalTitle nvarchar(200),
@DurationMinutes int,
@Year int,
@Imageurl nvarchar(max),
@IdMovie int OUTPUT
as
begin
	insert into Movie
	(Title, [Description], OriginalTitle, DurationMinutes, [Year], ImageUrl)
	values
	(@Title, @Description, @OriginalTitle,
	@DurationMinutes, @Year, @Imageurl)
	SET @IdMovie = SCOPE_IDENTITY()
end
go

create or alter proc SelectMovies
as
begin
	select * from Movie
end
go

create or alter proc SelectMovie
@IdMovie int
as
begin
	select * from Movie where IdMovie = @IdMovie
end
go

create or alter proc UpdateMovie
@IdMovie int,
@Title nvarchar(200),
@Description nvarchar(1000),
@OriginalTitle nvarchar(200),
@DurationMinutes int,
@Year int,
@Imageurl nvarchar(max)
as
begin
	update Movie
	set 
	Title = @Title,
	[Description] = @Description,
	OriginalTitle = @OriginalTitle,
	DurationMinutes = @DurationMinutes,
	[Year] = @Year,
	ImageUrl = @Imageurl
	where IdMovie = @IdMovie
end
go

create or alter proc DeleteMovie
@IdMovie int
as
begin
	delete from MovieActor where MovieId = @IdMovie
	delete from MovieGenre where MovieId = @IdMovie
	delete from Movie where IdMovie = @IdMovie
end
go

------------------------------------------
--GENRE

create or alter proc CreateGenre
@Name nvarchar(100),
@IdGenre int OUTPUT
as
begin
	insert into Genre values (@Name)
	SET @IdGenre = SCOPE_IDENTITY()
end
go

create or alter proc SelectGenre
@IdGenre int
as
begin
	select * from Genre where IdGenre = @IdGenre
end
go

create or alter proc SelectGenres
as
begin
	select * from Genre
end
go

create or alter proc UpdateGenre
@IdGenre int,
@Name nvarchar(100)
as
begin
	update Genre
	set [Name] = @Name
	where IdGenre = @IdGenre
end
go

create or alter proc DeleteGenre
@IdGenre int
as 
begin
	delete from MovieGenre where GenreId = @IdGenre
	delete from Genre where IdGenre = @IdGenre
end
go

------------------------------------------
--USER

create or alter proc SelectUser
@IdUser int
as
begin
	select * from [User] where IdUser = @IdUser
end
go

create or alter proc SelectUsers
as
begin
	select * from [User]
end
go

create or alter proc CreateAdmin
@username nvarchar(50),
@password nvarchar(50)
as
begin
	if((SELECT Count(*) FROM [User] where Username = @username) = 0)
	begin
		insert into [User] (Username, Password, AdminRole)
		values (@username, @password, 1)
	end
end
go

create or alter proc CreateUser
@Username nvarchar(50),
@Password nvarchar(50),
@IdUser int OUTPUT
as
begin
	if((SELECT Count(*) FROM [User] where Username = @username) = 0)
	begin
		insert into [User] (Username, Password, AdminRole)
		values (@username, @password, 0)
		SET @IdUser = SCOPE_IDENTITY()
	end
end
go
 

create or alter proc UpdateUser
@IdUser int,
@username nvarchar(50),
@password nvarchar(50)
as
begin
	update [User]
	set 
	Username = @username,
	[Password] = @password
	where IdUser = @IdUser
end
go

create or alter proc DeleteUser
@IdUser int
as
begin
	delete from [User] where IdUser = @IdUser
end
go

create or alter proc AuthenticateUser
@Username nvarchar (50),
@Password nvarchar (50),
@Result INT OUTPUT
as
begin

    --Check if the username and password exists
    SELECT @Result = 
        CASE
            WHEN EXISTS (
                SELECT 1
                FROM [User]
                WHERE Username = @Username AND [Password] = @Password
            ) THEN
                CASE
                    WHEN EXISTS (
                        SELECT 1
                        FROM [User]
                        WHERE Username = @Username
                        AND Password = @Password
                        AND AdminRole = 1
                    ) THEN 2 -- AdminRole = 1
                    ELSE 1 -- AdminRole = 0
                END
            ELSE 0 -- No such username and password
        END;

    -- Return the result
    SELECT top 1 @Result AS Result;
end
go


exec CreateAdmin 'admin', 'admin'
------------------------------------------
