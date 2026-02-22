
INSERT INTO users (firstname, lastname, username, user_creation, email)
VALUES
('Alice', 'Smith', 'alice01', '2026-01-01', 'alice@example.com'),
('Bob', 'Jones', 'bobby_j', '2026-01-02', 'bob@example.com'),
('Charlie', 'Brown', 'charles', '2026-01-03', 'charlie@example.com'),
('Diana', 'Prince', 'wonder_d', '2026-01-04', 'diana@example.com'),
('Ethan', 'Hunt', 'mission_e', '2026-01-05', 'ethan@example.com');



INSERT INTO user_group(owner_id, group_name, group_creation)
VALUES
    ('1','les patates','2026-01-02'),
    ('2','les tomates','2026-01-03');


INSERT INTO group_member(group_id, user_id)
VALUES
    ('1','2'),
    ('1','3'),
    ('2','4'),
    ('2','5');