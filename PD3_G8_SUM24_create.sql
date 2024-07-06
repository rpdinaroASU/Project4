CREATE TABLE RECORD_LABEL (
  LabelName varchar(25) NOT NULL,
  Address varchar(30),
  Phone varchar(11),
  PRIMARY KEY(LabelName)
);

CREATE TABLE ALBUM (
  AlbumID varchar(8) NOT NULL,
  AlbumName varchar(25) NOT NULL DEFAULT 'Untitled',
  LabelName varchar(25),
  PRIMARY KEY (AlbumID),
  FOREIGN KEY(LabelName) REFERENCES RECORD_LABEL(LabelName),
  CONSTRAINT CHK_AID
  	CHECK (AlbumID LIKE 'A__%')
);

CREATE TABLE SONG (
  SongID varchar(8) NOT NULL,
  ReleaseDate date,
  Title varchar(35) NOT NULL DEFAULT 'Untitled',
  AlbumID varchar(8),
  PRIMARY KEY(SongID),
  FOREIGN KEY(AlbumID) REFERENCES ALBUM(AlbumID),
  CONSTRAINT CHK_SID
  	CHECK (SongID LIKE 'S__%')
);

CREATE TABLE GENRE (
  GenreID varchar(8) NOT NULL,
  GenreName varchar(15) NOT NULL,
  PRIMARY KEY (GenreID),
  CONSTRAINT CHK_GID
  	CHECK (GenreID LIKE 'G__%')
);

CREATE TABLE SONG_GENRE (
  GenreID varchar(8) NOT NULL,
  SongID varchar(8) NOT NULL,
  PRIMARY KEY (GenreID, SongID),
  FOREIGN KEY(GenreID) REFERENCES GENRE(GenreID),
  FOREIGN KEY(SongID) REFERENCES SONG(SongID)
);

CREATE TABLE CONTRIBUTOR(
  ContributorID varchar(8) NOT NULL,
  FirstName varchar(15) NOT NULL DEFAULT 'Unknown',
  LastName varchar(15) NOT NULL DEFAULT 'Unknown',
  DOB date,
  PRIMARY KEY(ContributorID),
  CONSTRAINT CHK_CID
  	CHECK (ContributorID LIKE 'C__%')
);     

CREATE TABLE SONG_CONTRIBUTOR(
  SongID varchar(8) NOT NULL,
  ContributorID varchar(8) NOT NULL,
  Contribution ENUM('Main','Feature','Composer','Producer') NOT NULL,
  PRIMARY KEY(SongID,ContributorID,Contribution),
  FOREIGN KEY(SongID) REFERENCES SONG(SongID),
  FOREIGN KEY(ContributorID) REFERENCES CONTRIBUTOR(ContributorID)
);           

CREATE TABLE PLAYLIST(
  PLID varchar(8) NOT NULL,
  PLName varchar(25) NOT NULL DEFAULT 'Untitled',
  CreatorName varchar(20) NOT NULL DEFAULT 'Unknown',
  DateCreated datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(PLID),
  CONSTRAINT CHK_PID
  	CHECK (PLID LIKE 'PL___%')
);

CREATE TABLE SONG_PLAYLIST(
  PLID varchar(8) NOT NULL,
  SongID varchar(8) NOT NULL,
  DateAdded datetime NOT NULL DEFAULT now(),
  PRIMARY KEY (PLID,SongID,dateAdded),
  FOREIGN KEY(PLID) REFERENCES PLAYLIST(PLID),
  FOREIGN KEY(SongID) REFERENCES SONG(SongID)
);

