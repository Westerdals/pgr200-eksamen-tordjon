
-- coupling-table between timeslots and days, and between timeslots and days


drop table if exists timeslot_day;
drop table if exists conference_day;
drop table if exists talk_timeslot;
drop table if exists timeslot;
drop table if exists conference;
drop table if exists topic;
drop table if exists day;
drop table if exists talk;

create table talk (
  id uuid primary key,
  title varchar,
  description varchar,
  topic_title varchar
);

create table conference (
  id uuid primary key,
  name varchar
);

create table timeslot (
  id uuid primary key,
  start_time time,
  end_time time,
  talk_id uuid references talk(id)
);


create table day (
  id uuid primary key,
  date date
);


create table timeslot_day (
  day_id uuid references day(id),
  timeslot_id uuid references timeslot(id),

  primary key (day_id, timeslot_id)
);

create table talk_timeslot (
  timeslot_id uuid references timeslot(id),
  talk_id uuid references talk(id),

  primary key (timeslot_id, talk_id)
);

create table conference_day (
  conference_id uuid references conference(id),
  day_id uuid references day(id),

  primary key (conference_id, day_id)
);