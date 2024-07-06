INSERT INTO RECORD_LABEL (LabelName, Address, Phone)VALUES
('Record Label 1', 'Santa Monica, CA', '3108654000'),
('Record Label 2', 'New York, NY', '2128338000'),
('Record Label 3', 'New York, NY', '2122752000'),
('Record Label 4', 'Topeka, KS', '1118338000');

INSERT INTO ALBUM (AlbumID,AlbumName,LabelName) VALUES
('A001','Album 1', 'Record Label 1'),
('A002','Album 2', 'Record Label 2'),
('A003','Album 3', 'Record Label 1'),
('A004','Album 4', 'Record Label 3');

INSERT INTO SONG (SongID, ReleaseDate, Title, AlbumID) VALUES
('S001', '2023-05-01', 'Song A', 'A001'),
('S002', '2023-06-15', 'Song B', 'A001'),
('S003', '2023-07-20', 'Song C', 'A002'),
('S004', '2023-08-10', default, 'A004');

INSERT INTO GENRE(GenreID,GenreName) VALUES
('G001','Pop'),
('G002','Rock'),
('G003','Jazz');

INSERT INTO SONG_GENRE(GenreID,SongID) VALUES
('G001', 'S001'),
('G002', 'S002'),
('G003', 'S003');

INSERT INTO CONTRIBUTOR(ContributorID,FirstName,LastName,DOB) VALUES
('C001','FName A', 'LName A', '2000-01-04'),
('C002','FName B', 'LName B', '2000-02-03'),
('C003','FName C', 'LName C', '2000-03-02'),
('C004','FName D', 'LName D', '2000-04-01');

INSERT INTO SONG_CONTRIBUTOR(SongID,ContributorID,Contribution) VALUES
('S001','C001','Main'),
('S001','C002','Feature'),
('S001','C004','Composer'),
('S002','C001','Main'),
('S002','C003','Producer'),
('S003','C004','Composer'),
('S003','C001','Main'),
('S004','C002','Producer'),
('S004','C004','Composer');

INSERT INTO PLAYLIST(PLID,PLName,CreatorName,DateCreated) VALUES
('PL001','PlayList One','Creator Name',default),
('PL002','PlayList 2','Creator Name 2','2024-02-24'),
('PL003',default,default,default);

INSERT INTO SONG_PLAYLIST(SongID,PLID,DateAdded) VALUES
('S001','PL001','2024-02-24'),
('S003','PL001',default),
('S004','PL003',default),
('S002','PL002',default);

