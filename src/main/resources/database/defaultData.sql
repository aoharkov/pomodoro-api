INSERT INTO users VALUES
(1, 'admin@gmail.com','admin', '$2a$10$Kk/uIwUpwQNEvj8rsBcj0uSyw1yYohrzgAwxBjP/5Jg3y5GVsLejS', 'ADMIN',
'2020-05-10 10:23:54+02', NULL, 1500),
(2, 'user@gmail.com', 'user', '$2a$10$MfntVMztuRLec8s/fviRPe1FtlZVhgRAlfjS4XYwJUTmvr5vsFZ36', 'USER',
'2020-06-10 10:23:54+02', NULL, 1500);

INSERT INTO spheres VALUES
(1, 'default');

INSERT INTO categories VALUES
(1, 2, 1, 'work'),
(2, 2, 1, 'sport');

INSERT INTO pomodoros VALUES
(1, 2, 1, 'do something useful', 1500, 0, 'STARTED', '2020-06-10 10:30:54+02', '2020-06-10 10:30:54+02', NULL)
