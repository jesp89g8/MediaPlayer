create table table_Songlist
(
    fldSongListID int not null
        constraint table_Songlist_pk
            primary key nonclustered,
    fldMusicID    int
        constraint table_Songlist_table_music_fldMusicID_fk
            references table_music,
    fldPlaylistID int
        constraint table_Songlist_table_Playlist_fldPlaylistID_fk
            references table_Playlist
)
go

INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (1, 1, 1);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (2, 2, 1);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (3, 3, 1);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (4, 4, 1);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (5, 5, 1);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (6, 6, 2);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (7, 7, 2);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (8, 8, 2);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (9, 9, 2);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (10, 10, 2);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (11, 11, 3);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (12, 12, 3);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (13, 13, 3);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (14, 14, 3);
INSERT INTO database_MusicLibrary.dbo.table_Songlist (fldSongListID, fldMusicID, fldPlaylistID) VALUES (15, 15, 3);