INSERT INTO `beer` (`id`, `alcoholLevel`, `capacity`, `comment`, `discountAmount`, `name`, `price`, `legendary`) VALUES
(1, 11.6, 0.5, 'A vil�g egyik leger�sebb s�re.', 0, 'Ultra s�r', 400, b'0'),
(2, 6.5, 0.5, 'A vil�g f�leg Eur�pai orsz�gaiban kedvelt s�r.', 0, 'Bivaly s�r', 250, b'0'),
(3, 5.7, 0.5, 'Egy igaz�n habos s�r az unalmas h�tk�znapokra.', 0, 'Habos s�r', 299, b'0'),
(4, 6.4, 0.33, 'Egy j� buliban mindig van sz�ks�g egy j� pofa s�rre.', 0, 'J�pofa s�r', 320, b'0'),
(5, 7.2, 0.5, 'Az egyik legfinomabb barna s�r amit inni fog.', 33, 'Bebarnult s�r', 399, b'0'),
(6, 2.2, 0.5, 'Egy igaz�n j�l elk�sz�tett lime �zes�t�s� s�r.', 0, 'Lime s�r', 235, b'0'),
(7, 5.6, 0.5, 'Egy igaz�n habosra k�sz�tett s�r�cske.', 0, 'Meghabosodott s�r', 259, b'0'),
(8, 4.9, 50, 'A ny�ri bulik egyik alapvet� kell�ke, egy 50 literes party hord�.', 50, 'Party hord�', 15999, b'1'),
(9, 4.5, 25, 'Egy meleg ny�ri d�lut�non a haverokkal ak�r 25 litert is k�pesek vagyunk elfogyasztani, j�ghidegen a legjobb.', 0, '25 L hord�', 8000, b'0'),
(10, 3.6, 0.5, 'A mindennapok szerencs�ire val� h�s�t� ital. (http://www.iconka.com)', 0, 'Szerencse s�r', 310, b'0'),
(11, 5.5, 50, 'Csappal ell�tott party hord�.', 0, 'Csapos party hord�', 19000, b'1'),
(12, 3.6, 0.5, 'A legjobb bar�tok szerencse s�re, most p�rban. (http://www.iconka.com)', 0, 'Bar�ts�g s�r', 459, b'0'),
(13, 4.9, 0.5, 'Igaz�n h�s�t� dobozos s�r�cske.', 0, 'J�ghegy s�r', 259, b'0'),
(14, 4.5, 0.5, 'H�s�t�, okt�beri finoms�g.', 15, '�szi s�r', 359, b'0'),
(15, 7.6, 0.5, 'Kezdetben feh�r azt�n pedig barna s�r is ak�r.', 0, 'Feh�r-Barna s�r', 320, b'0'),
(16, 2.4, 0.33, 'S�rga s�r, az unalmas h�tk�znapokra.', 0, 'S�rga s�r', 240, b'0');

INSERT INTO `storageitem` (`id`, `quantity`, `beer_id`) VALUES
(1, 122, 1),
(2, 16, 2),
(3, 0, 3),
(4, 60, 4),
(5, 31, 5),
(6, 16, 6),
(7, 0, 7),
(8, 63, 8),
(9, 3, 9),
(10, 13, 10),
(11, 21, 11),
(12, 43, 12),
(13, 10, 13),
(14, 16, 14),
(15, 23, 15),
(16, 30, 16);

INSERT INTO `cart` (`id`, `user_id`) VALUES
(1, null);


INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO `user` (`id`, `dateOfBirth`, `email`, `password`, `points`, `username`, `experiencePoints`, `cart_id`, `money`) VALUES
(1, '1995-10-20 00:00:00', 'holi60@gmail.com', '$2a$10$2xgwOhINMMmb8L9jXcoXsO7w0ie3418DOU0q3hZ7IxYF9ULGlEw76', 0, 'Holi60', '750.00', null, '115500.00');

INSERT INTO `user_role` (`User_id`, `roles_id`) VALUES
(1, 1),
(1, 2);

UPDATE `cart` SET `user_id` = 1 where `id` = 1;
UPDATE `user` SET `cart_id` = 1 WHERE `id` = 1;



