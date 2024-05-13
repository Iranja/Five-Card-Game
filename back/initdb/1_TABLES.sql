CREATE TABLE "User" (
                      id SERIAL PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(100) NOT NULL
);

CREATE TABLE Game (
                      id SERIAL PRIMARY KEY,
                      user_id INT NOT NULL,
                      place VARCHAR(100),
                      points INT,
                      date DATE,
                      time TIME,
                      FOREIGN KEY (user_id) REFERENCES "User"(id)
);

/*
create table students
(
    id SERIAL PRIMARY KEY,
    first_name TEXT not null,
    last_name TEXT not null,
    birthdate date null,
    major_id int null,
    image bytea null
);

create table majors
(
    id SERIAL PRIMARY KEY,
    name TEXT not null,
    description TEXT not null
);

create table courses
(
    id SERIAL PRIMARY KEY,
    name TEXT not null,
    hours int not null
);

create table student_course
(
    id SERIAL PRIMARY KEY,
    student_id int not null,
    course_id int not null
);
*/