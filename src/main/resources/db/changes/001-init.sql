--liquibase formatted sql

--changeset k4t4u:001_1

create table if not exists categories (
	id   uuid not null
		primary key,
	name varchar(255)
);
create table if not exists questions (
	id          uuid not null
		primary key,
	created     timestamp,
	modified    timestamp,
	name        varchar(255),
	category_id uuid
		constraint fkctl6tuf74n8cufkb3ulj6b3fc
			references categories
);
create table if not exists answers (
	id          uuid not null
		primary key,
	name        varchar(255),
	question_id uuid
		constraint fk3erw1a3t0r78st8ty27x6v3g1
			references questions
);

--changeset k4t4u:001_2
delete from answers;
delete from questions;
delete from categories;

--changeset k4t4u:001_3
insert into categories (id, name) values
	(gen_random_uuid(), 'Health'),
	(gen_random_uuid(), 'Animals'),
	(gen_random_uuid(), 'Tourism'),
	(gen_random_uuid(), 'Beauty and Style'),
	(gen_random_uuid(), 'Culture'),
	(gen_random_uuid(), 'Education'),
	(gen_random_uuid(), 'Games'),
	(gen_random_uuid(), 'Hobby'),
	(gen_random_uuid(), 'House and garden'),
	(gen_random_uuid(), 'Business'),
	(gen_random_uuid(), 'Finances'),
	(gen_random_uuid(), 'Culinary'),
	(gen_random_uuid(), 'Computers'),
	(gen_random_uuid(), 'Personal'),
	(gen_random_uuid(), 'Automotive'),
	(gen_random_uuid(), 'Policy'),
	(gen_random_uuid(), 'Work'),
	(gen_random_uuid(), 'Presents'),
	(gen_random_uuid(), 'Shopping'),
	(gen_random_uuid(), 'Electronics'),
	(gen_random_uuid(), 'Entertainment'),
	(gen_random_uuid(), 'Sex'),
	(gen_random_uuid(), 'Relationships'),
	(gen_random_uuid(), 'Other');

insert into questions (id, name, category_id) values
	(gen_random_uuid(), 'Where is the best place to spend your holidays in Poland', (select id from categories where name = 'Tourism')),
	(gen_random_uuid(), 'Where is the best place to spend your holidays in Europe', (select id from categories where name = 'Tourism'));
