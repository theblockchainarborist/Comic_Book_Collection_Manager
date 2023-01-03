BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('connor', '$2a$10$ysmj.21LVAAekdiHsJsbveIF6ylsnGleJ0SN7IFmXVf1oSzA..5d.', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('sumeet', '$2a$10$ysmj.21LVAAekdiHsJsbveIF6ylsnGleJ0SN7IFmXVf1oSzA..5d.', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('lisa', '$2a$10$ysmj.21LVAAekdiHsJsbveIF6ylsnGleJ0SN7IFmXVf1oSzA..5d.', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('gabi', '$2a$10$ysmj.21LVAAekdiHsJsbveIF6ylsnGleJ0SN7IFmXVf1oSzA..5d.', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('sam', '$2a$10$ysmj.21LVAAekdiHsJsbveIF6ylsnGleJ0SN7IFmXVf1oSzA..5d.', 'ROLE_USER');

COMMIT TRANSACTION;




