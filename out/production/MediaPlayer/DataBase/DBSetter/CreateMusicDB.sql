USE [master]
GO
/****** Object:  Database [database_MusicLibrary]    Script Date: 18-01-2021 10:09:26 ******/
CREATE DATABASE [database_MusicLibrary]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'database_MusicLibrary', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\database_MusicLibrary.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'database_MusicLibrary_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\database_MusicLibrary_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [database_MusicLibrary] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [database_MusicLibrary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [database_MusicLibrary] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [database_MusicLibrary] SET ANSI_NULLS OFF
GO
ALTER DATABASE [database_MusicLibrary] SET ANSI_PADDING OFF
GO
ALTER DATABASE [database_MusicLibrary] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [database_MusicLibrary] SET ARITHABORT OFF
GO
ALTER DATABASE [database_MusicLibrary] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [database_MusicLibrary] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [database_MusicLibrary] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [database_MusicLibrary] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [database_MusicLibrary] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [database_MusicLibrary] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [database_MusicLibrary] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [database_MusicLibrary] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [database_MusicLibrary] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [database_MusicLibrary] SET  ENABLE_BROKER
GO
ALTER DATABASE [database_MusicLibrary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [database_MusicLibrary] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [database_MusicLibrary] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [database_MusicLibrary] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [database_MusicLibrary] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [database_MusicLibrary] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [database_MusicLibrary] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [database_MusicLibrary] SET RECOVERY FULL
GO
ALTER DATABASE [database_MusicLibrary] SET  MULTI_USER
GO
ALTER DATABASE [database_MusicLibrary] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [database_MusicLibrary] SET DB_CHAINING OFF
GO
ALTER DATABASE [database_MusicLibrary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [database_MusicLibrary] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [database_MusicLibrary] SET DELAYED_DURABILITY = DISABLED
GO
EXEC sys.sp_db_vardecimal_storage_format N'database_MusicLibrary', N'ON'
GO
ALTER DATABASE [database_MusicLibrary] SET QUERY_STORE = OFF
GO
USE [database_MusicLibrary]
GO
/****** Object:  Table [dbo].[table_music]    Script Date: 18-01-2021 10:09:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[table_music](
	[fldMusicID] [int] NOT NULL,
	[fldMusicName] [varchar](90) NULL,
	[fldArtist] [varchar](80) NULL,
	[fldType] [varchar](30) NULL,
	[fldOffer] [varchar](10) NULL,
	[fldPath] [varchar](200) NULL,
	[fldLanguage] [varchar](30) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[table_Playlist]    Script Date: 18-01-2021 10:09:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[table_Playlist](
	[fldPlaylistID] [int] NOT NULL,
	[fldPlaylistName] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[table_Songlist]    Script Date: 18-01-2021 10:09:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[table_Songlist](
	[fldSongListID] [int] NOT NULL,
	[fldMusicID] [int] NULL,
	[fldPlaylistID] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (1, N'Here,After,Us', N'Mayday', N'Rock', N'Fei', N'src/DataBase/MusicSample/FromFei/Here, After, Us - Mayday.mp3', N'Chinese')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (2, N'Mojito', N'JayChou', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/Mojito - JayChou.mp3', N'Chinese')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (3, N'Should''ve let Go', N'JJ&Jacksen', N'R&B', N'Fei', N'src/DataBase/MusicSample/FromFei/Should''ve Let Go - JJ & JacksenWang.mp3', N'Chinese')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (4, N'The wind rises', N'QingFeng Wu', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/THE WIND RISES QingFengWu.mp3', N'Chinese')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (5, N'You better not think about me', N'Hebe Tien', N'Pop', N'Fei', N'src/DataBase/MusicSample/FromFei/You Better Not Think About Me - Hebe Tien.mp3', N'Chinese')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (6, N'One call away', N'Charlie Puth', NULL, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Charlie Puth - One Call Away [Official Video].mp3', N'English')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (7, N'Supermarket Flowers', N'Ed Sheeran', NULL, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Ed Sheeran - Supermarket Flowers [Official Audio].mp3', N'English')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (8, N'I hate U , I love U', N'Gnash ft., Olivia O''Brien', NULL, N'Jesper', N'src/DataBase/MusicSample/FromJesper/gnash - i hate u, i love u ft. olivia o''brien (music video).mp3', N'English')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (9, N'Mercy', N'Shawn Mendes', NULL, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Shawn Mendes - Mercy.mp3', N'English')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (10, N'Youth', N'Troye Sivan', NULL, N'Jesper', N'src/DataBase/MusicSample/FromJesper/Troye Sivan - YOUTH (Official Video).mp3', N'English')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (11, N'Baazigar O Baazigar', N'Shahrukh Khan', NULL, N'Zia', N'src/DataBase/MusicSample/FromZia/Baazigar O Baazigar-HD VIDEO SONG Shahrukh Khan & Kajol Baazigar 90''s Superhit Hindi Love.mp3', N'India')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (12, N'Chaiyya Chaiyya', N'Shahrukh Khan', NULL, N'Zia', N'src/DataBase/MusicSample/FromZia/Chaiyya Chaiyya Full Video Song Dil Se Shahrukh Khan, Malaika Arora Khan Sukhwinder Singh.mp3', N'India')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (13, N'Challa', N'Shahrukh Khan', NULL, N'Zia', N'src/DataBase/MusicSample/FromZia/Challa Full Song Jab Tak Hai Jaan Shah Rukh Khan, Katrina Kaif Rabbi A. R. Rahman Gu.mp3', N'India')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (14, N'Humko Humise Chura Lo', N'Shahrukh Khan', NULL, N'Zia', N'src/DataBase/MusicSample/FromZia/Humko Humise Chura Lo Full Song Mohabbatein Shah Rukh Khan, Aishwarya Lata Mangeshkar, U.mp3', N'India')
GO
INSERT [dbo].[table_music] ([fldMusicID], [fldMusicName], [fldArtist], [fldType], [fldOffer], [fldPath], [fldLanguage]) VALUES (15, N'Main Yahaan Hoon', N'Shahrukh Khan', NULL, N'Zia', N'src/DataBase/MusicSample/FromZia/Main Yahaan Hoon Full Song Veer-Zaara Shah Rukh Khan, Preity Zinta Madan Mohan, Udit Nar.mp3', N'India')
GO
INSERT [dbo].[table_Playlist] ([fldPlaylistID], [fldPlaylistName]) VALUES (1, N'Chinese song')
GO
INSERT [dbo].[table_Playlist] ([fldPlaylistID], [fldPlaylistName]) VALUES (2, N'English song')
GO
INSERT [dbo].[table_Playlist] ([fldPlaylistID], [fldPlaylistName]) VALUES (3, N'Indian song')
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (1, 1, 1)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (2, 2, 1)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (3, 3, 1)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (4, 4, 1)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (5, 5, 1)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (6, 6, 2)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (7, 7, 2)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (8, 8, 2)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (9, 9, 2)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (10, 10, 2)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (11, 11, 3)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (12, 12, 3)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (13, 13, 3)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (14, 14, 3)
GO
INSERT [dbo].[table_Songlist] ([fldSongListID], [fldMusicID], [fldPlaylistID]) VALUES (15, 15, 3)
GO
/****** Object:  Index [table_music_pk]    Script Date: 18-01-2021 10:09:26 ******/
ALTER TABLE [dbo].[table_music] ADD  CONSTRAINT [table_music_pk] PRIMARY KEY NONCLUSTERED
(
	[fldMusicID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [table_Playlist_pk]    Script Date: 18-01-2021 10:09:26 ******/
ALTER TABLE [dbo].[table_Playlist] ADD  CONSTRAINT [table_Playlist_pk] PRIMARY KEY NONCLUSTERED
(
	[fldPlaylistID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [table_Songlist_pk]    Script Date: 18-01-2021 10:09:26 ******/
ALTER TABLE [dbo].[table_Songlist] ADD  CONSTRAINT [table_Songlist_pk] PRIMARY KEY NONCLUSTERED
(
	[fldSongListID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[table_Songlist]  WITH CHECK ADD  CONSTRAINT [table_Songlist_table_music_fldMusicID_fk] FOREIGN KEY([fldMusicID])
REFERENCES [dbo].[table_music] ([fldMusicID])
GO
ALTER TABLE [dbo].[table_Songlist] CHECK CONSTRAINT [table_Songlist_table_music_fldMusicID_fk]
GO
ALTER TABLE [dbo].[table_Songlist]  WITH CHECK ADD  CONSTRAINT [table_Songlist_table_Playlist_fldPlaylistID_fk] FOREIGN KEY([fldPlaylistID])
REFERENCES [dbo].[table_Playlist] ([fldPlaylistID])
GO
ALTER TABLE [dbo].[table_Songlist] CHECK CONSTRAINT [table_Songlist_table_Playlist_fldPlaylistID_fk]
GO
USE [master]
GO
ALTER DATABASE [database_MusicLibrary] SET  READ_WRITE
GO
