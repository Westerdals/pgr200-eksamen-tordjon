-- no.kristiania.pgr200.server.database now has uuid as ids

create table if not exists talk (
  id uuid primary key,
  title varchar,
  description varchar,
  topic_title varchar
);


create table if not exists topic (
  id uuid primary key,
  name varchar,
  description varchar
);


create table if not exists timeslot (
  id uuid primary key,
  talk_id varchar references talk(id)
);

create table if not exists day (
  id uuid primary key,
  date date
);

create table if not exists timeslot_day (
  id uuid primary key,
  day_id uuid references day(id),
  timeslot_id uuid references timeslot(id)
);

