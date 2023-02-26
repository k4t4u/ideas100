--liquibase formatted sql

--changeset k4t4u:002_1
insert into questions (id, name, category_id) values
	(gen_random_uuid(), 'Why is it worth learning programming', (select id from categories where name = 'Education')),
	(gen_random_uuid(), 'Why Java is a good language to start', (select id from categories where name = 'Education'));

insert into questions (id, name, category_id) values
	(gen_random_uuid(), 'What are the healthiest vegetables?', (select id from categories where name = 'Health'));

insert into answers (id, name, question_id) values
	(gen_random_uuid(), 'Carrot', (select id from questions where name = 'What are the healthiest vegetables?')),
	(gen_random_uuid(), 'Broccoli', (select id from questions where name = 'What are the healthiest vegetables?')),
	(gen_random_uuid(), 'Pumpkin', (select id from questions where name = 'What are the healthiest vegetables?')),
	(gen_random_uuid(), 'Pea', (select id from questions where name = 'What are the healthiest vegetables?'));

insert into answers (id, name, question_id) values
	(gen_random_uuid(), 'Gdańsk', (select id from questions where name = 'Where is the best place to spend your holidays in Poland')),
	(gen_random_uuid(), 'Bieszczady', (select id from questions where name = 'Where is the best place to spend your holidays in Poland')),
	(gen_random_uuid(), 'Mazury', (select id from questions where name = 'Where is the best place to spend your holidays in Poland'));

