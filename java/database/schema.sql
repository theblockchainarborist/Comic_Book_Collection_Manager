BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS collections;
DROP TABLE IF EXISTS comic_book;
DROP TABLE IF EXISTS collection_comic_book;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE collections (
	id SERIAL,
	name varchar(50) NOT NULL,
	user_id int,
	status varchar(50) NOT NULL,
	CONSTRAINT PK_user_collection PRIMARY KEY (id)
);


CREATE TABLE comic_book (
	id SERIAL,
	api_id int NOT NULL UNIQUE,
	CONSTRAINT PK_comic_book PRIMARY KEY (id)
);


CREATE TABLE collection_comic_book (
	collections_id int,
	comic_book_id int,
	CONSTRAINT PK_collection_comic_book PRIMARY KEY (collections_id, comic_book_id)
);


-- Foreign Keys

ALTER TABLE collections ADD CONSTRAINT FK_user_collections FOREIGN KEY(user_id) REFERENCES users(user_id);
ALTER TABLE collection_comic_book ADD CONSTRAINT FK_collections_id FOREIGN KEY(collections_id) REFERENCES collections(id);
ALTER TABLE collection_comic_book ADD CONSTRAINT FK_comic_book_id FOREIGN KEY(comic_book_id) REFERENCES comic_book(id);

-- Add NOT NULL Constraints

ALTER TABLE collections ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE collection_comic_book ALTER COLUMN collections_id SET NOT NULL;
ALTER TABLE collection_comic_book ALTER COLUMN comic_book_id SET NOT NULL;

COMMIT TRANSACTION;


