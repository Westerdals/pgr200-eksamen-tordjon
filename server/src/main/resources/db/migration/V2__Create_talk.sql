create table if not exists talk (
  title varchar primary key,
  description varchar,
  topic_title varchar
);


create table if not exists topicTitle (
  name varchar primary key,
  description varchar
);


create table if not exists timeslot (
  id serial primary key,
  talk varchar references talk(title)
);

create table if not exists day (
  date date primary key
);

create table if not exists timeslot_day (
  day date references day(date),
  timeslot serial references timeslot(id)
);

