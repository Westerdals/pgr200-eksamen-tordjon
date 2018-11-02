-- database now has uuid as ids
drop table if exists timeslot_day;
drop table if exists timeslot;
drop table if exists topic;
drop table if exists day;
drop table if exists talk;

create table talk (
  id uuid primary key,
  title varchar,
  description varchar,
  topic_title varchar
);


create table timeslot (
  id uuid primary key,
  start_time time,
  end_time time,
  talk_id uuid references talk(id)
);
-- select id, title, description, topic_title from topic

create table topic (
  id uuid primary key,
  name varchar,
  description varchar
);


create table day (
  id uuid primary key,
  year int,
  month int,
  dayOfMonth int
);


create table timeslot_day (
  id uuid primary key,
  day_id uuid references day(id),
  timeslot_id uuid references timeslot(id)
);

