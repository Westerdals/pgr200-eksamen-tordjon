create table if not exists talk (
  title varchar primary key,
  description varchar,
  topic_title varchar
);


create table if not exists topicTitle (
  name varchar primary key,
  description varchar
);