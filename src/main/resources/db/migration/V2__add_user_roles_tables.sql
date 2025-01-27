
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username TEXT UNIQUE,
                       password TEXT NOT NULL,
                       enable BOOLEAN NOT NULL
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name TEXT UNIQUE NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);


INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

INSERT INTO users (username, password, enable)
VALUES ('admin', '$2a$10$vP2DvxvUWRLjQ.XloceHLucYt8bGbbg3njcqJwXpn6vN81Rhigl/q', TRUE);

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 2);
