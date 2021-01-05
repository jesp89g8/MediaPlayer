create table table_Playlist
(
    fldPlaylistID   int not null
        constraint table_Playlist_pk
            primary key nonclustered,
    fldPlaylistName varchar(50)
)
go

INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (1, N'choose by fei');
INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (2, N'choose by Jesper');
INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (3, N'choose by zia');
INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (4, N'chinese song');
INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (5, N'indea song');
INSERT INTO database_MusicLibrary.dbo.table_Playlist (fldPlaylistID, fldPlaylistName) VALUES (6, N'english song');