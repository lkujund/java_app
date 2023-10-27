use JavaMovies
go

create or alter proc ClearData
as
begin
truncate table MovieGenre

truncate table MovieActor

truncate table MovieDirector

truncate table Director

truncate table Movie

truncate table Actor

truncate table Genre
end
go