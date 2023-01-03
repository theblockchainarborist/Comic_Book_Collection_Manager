BEGIN TRANSACTION;

INSERT INTO collections (name, user_id, status) VALUES ('Private Comics', 1, 'private');
INSERT INTO collections (name, user_id, status) VALUES (' My Comic Collection 2', 2, 'public');

INSERT INTO collections (name, user_id, status) VALUES ('Spider-Girl', 6, 'public');
INSERT INTO collections (name, user_id, status) VALUES ('Dark Tower', 5, 'public');
INSERT INTO collections (name, user_id, status) VALUES ('Ms. Marvel', 4, 'public');
INSERT INTO collections (name, user_id, status) VALUES ('Shang-Chi', 7, 'public');
INSERT INTO collections (name, user_id, status) VALUES ('Comic-Box', 3, 'public');



INSERT INTO comic_book (api_id) VALUES (291);
INSERT INTO comic_book (api_id) VALUES (184);
INSERT INTO comic_book (api_id) VALUES (225);
INSERT INTO comic_book (api_id) VALUES (376);
INSERT INTO comic_book (api_id) VALUES (332);

-- Spider Girl
INSERT INTO comic_book (api_id) VALUES (735);
INSERT INTO comic_book (api_id) VALUES (529);
INSERT INTO comic_book (api_id) VALUES (764);
INSERT INTO comic_book (api_id) VALUES (858);
INSERT INTO comic_book (api_id) VALUES (922);
-- Dark Tower
INSERT INTO comic_book (api_id) VALUES (51648);
INSERT INTO comic_book (api_id) VALUES (51620);
INSERT INTO comic_book (api_id) VALUES (51651);
INSERT INTO comic_book (api_id) VALUES (51650);
INSERT INTO comic_book (api_id) VALUES (51649);
-- Ms. Marvel
INSERT INTO comic_book (api_id) VALUES (98749);
INSERT INTO comic_book (api_id) VALUES (103513);
INSERT INTO comic_book (api_id) VALUES (62193);
INSERT INTO comic_book (api_id) VALUES (103827);
INSERT INTO comic_book (api_id) VALUES (103514);
-- Shang-Chi
INSERT INTO comic_book (api_id) VALUES (103523);
INSERT INTO comic_book (api_id) VALUES (105307);
INSERT INTO comic_book (api_id) VALUES (105719);
INSERT INTO comic_book (api_id) VALUES (103522);
INSERT INTO comic_book (api_id) VALUES (105041);


COMMIT TRANSACTION;

BEGIN TRANSACTION;

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 1), (SELECT id FROM comic_book WHERE id = 1));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 2), (SELECT id FROM comic_book WHERE id = 2));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 2), (SELECT id FROM comic_book WHERE id = 3));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 2), (SELECT id FROM comic_book WHERE id = 4));


INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 1), (SELECT id FROM comic_book WHERE id = 5));

-- Spider-Girl Collection
INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 3), (SELECT id FROM comic_book WHERE id = 6));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 3), (SELECT id FROM comic_book WHERE id = 7));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 3), (SELECT id FROM comic_book WHERE id = 8));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 3), (SELECT id FROM comic_book WHERE id = 9));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 3), (SELECT id FROM comic_book WHERE id = 10));

-- Dark Tower
INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 4), (SELECT id FROM comic_book WHERE id = 11));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 4), (SELECT id FROM comic_book WHERE id = 12));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 4), (SELECT id FROM comic_book WHERE id = 13));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 4), (SELECT id FROM comic_book WHERE id = 14));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 4), (SELECT id FROM comic_book WHERE id = 15));

-- Ms. Marvel
INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 5), (SELECT id FROM comic_book WHERE id = 16));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 5), (SELECT id FROM comic_book WHERE id = 17));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 5), (SELECT id FROM comic_book WHERE id = 18));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 5), (SELECT id FROM comic_book WHERE id = 19));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 5), (SELECT id FROM comic_book WHERE id = 20));

-- Shang-Chi
INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 6), (SELECT id FROM comic_book WHERE id = 21));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 6), (SELECT id FROM comic_book WHERE id = 22));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 6), (SELECT id FROM comic_book WHERE id = 23));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 6), (SELECT id FROM comic_book WHERE id = 24));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 6), (SELECT id FROM comic_book WHERE id = 25));

-- Mixed data
INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 7), (SELECT id FROM comic_book WHERE id = 2));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 7), (SELECT id FROM comic_book WHERE id = 12));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 7), (SELECT id FROM comic_book WHERE id = 20));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 7), (SELECT id FROM comic_book WHERE id = 15));

INSERT INTO collection_comic_book (collections_id, comic_book_id)
VALUES ((SELECT id FROM collections WHERE id = 7), (SELECT id FROM comic_book WHERE id = 8));

COMMIT TRANSACTION;
