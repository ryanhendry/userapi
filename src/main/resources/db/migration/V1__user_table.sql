create table user (
    id UUID not null,
    username varchar(100) not null,
    password varchar(100) not null,
    dob date not null,
    pan varchar(19)
);