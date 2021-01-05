create table table_music
(
    fldMusicID   int not null
        constraint table_music_pk
            primary key nonclustered,
    fldMusicName varchar(90),
    fldArtist    varchar(80),
    fldType      varchar(30),
    fldOffer     varchar(10),
    fldPath      varchar(200),
    fldLanguage  varchar(30)
)
go

INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (1, N'Here,After,Us', N'Mayday', N'Rock', N'Fei', N'src/DataBase/MusicSample/FromFei/Here, After, Us - Mayday.mp3', N'Chinese');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (2, N'Mojito', N'JayChou', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/Mojito - JayChou.mp3', N'Chinese');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (3, N'Should''ve let Go', N'JJ&Jacksen', N'R&B', N'Fei', N'src/DataBase/MusicSample/FromFei/Should''ve Let Go - JJ & JacksenWang.mp3', N'Chinese');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (4, N'The wind rises', N'QingFeng Wu', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/THE WIND RISES QingFengWu.mp3', N'Chinese');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (5, N'You better not think about me', N'Hebe Tien', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/You Better Not Think About Me - Hebe Tien.mp3', N'Chinese');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (6, N'One call away', N'Charlie Puth', null, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Charlie Puth - One Call Away [Official Video].mp3', N'English');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (7, N'Supermarket Flowers', N'Ed Sheeran', null, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Ed Sheeran - Supermarket Flowers [Official Audio].mp3', N'English');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (8, N'I hate U , I love U', N'Gnash ft., Olivia O''Brien', null, N'Jesper', N'src/DataBase/MusicSample/FromJesper/gnash - i hate u, i love u ft. olivia o''brien (music video).mp3', N'English');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (9, N'Mercy', N'Shawn Mendes', null, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Shawn Mendes - Mercy.mp3', N'English');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (10, N'Youth', N'Troye Sivan', null, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Troye Sivan - YOUTH (Official Video).mp3', N'English');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (11, N'Baazigar O Baazigar', N'Shahrukh Khan', null, N'Zia', N'src/DataBase/MusicSample/FromZia/Baazigar O Baazigar-HD VIDEO SONG Shahrukh Khan & Kajol Baazigar 90''s Superhit Hindi Love.mp3', N'India');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (12, N'Chaiyya Chaiyya', N'Shahrukh Khan', null, N'Zia', N'src/DataBase/MusicSample/FromZia/Chaiyya Chaiyya Full Video Song Dil Se Shahrukh Khan, Malaika Arora Khan Sukhwinder Singh.mp3', N'India');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (13, N'Challa', N'Shahrukh Khan', null, N'Zia', N'src/DataBase/MusicSample/FromZia/Challa Full Song Jab Tak Hai Jaan Shah Rukh Khan, Katrina Kaif Rabbi A. R. Rahman Gu.mp3', N'India');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (14, N'Humko Humise Chura Lo', N'Shahrukh Khan', null, N'Zia', N'src/DataBase/MusicSample/FromZia/Humko Humise Chura Lo Full Song Mohabbatein Shah Rukh Khan, Aishwarya Lata Mangeshkar, U.mp3', N'India');
INSERT INTO database_MusicLibrary.dbo.table_music (fldMusicID, fldMusicName, fldArtist, fldType, fldOffer, fldPath, fldLanguage) VALUES (15, N'Main Yahaan Hoon', N'Shahrukh Khan', null, N'Zia', N'src/DataBase/MusicSample/FromZia/Main Yahaan Hoon Full Song Veer-Zaara Shah Rukh Khan, Preity Zinta Madan Mohan, Udit Nar.mp3', N'India');