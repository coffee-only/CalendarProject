SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE user_group;
TRUNCATE TABLE group_member;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (firstname, lastname, username, user_creation, email)
VALUES
('Alice', 'Smith', 'alice01', '2026-01-01', 'alice@example.com'),
('Bob', 'Jones', 'bobby_j', '2026-01-02', 'bob@example.com'),
('Charlie', 'Brown', 'charles', '2026-01-03', 'charlie@example.com'),
('Diana', 'Prince', 'wonder_d', '2026-01-04', 'diana@example.com'),
('Ethan', 'Hunt', 'mission_e', '2026-01-05', 'ethan@example.com');



INSERT INTO user_group(group_name, group_creation)
VALUES
    ('les patates','2026-01-02'),
    ('les tomates','2026-01-03'),
    ('les legumes','2026-01-03');


INSERT INTO group_member(group_id, user_id, group_role)
VALUES
    (1,1,'OWNER'),
    (1,3,'MEMBER'),
    (2,4,'OWNER'),
    (2,1,'MEMBER'),
    (3,2,'OWNER');