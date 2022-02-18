create table users(
    user_no number,
    id varchar2(50) not null unique,
    user_name varchar2(100) not null,
    password varchar2(50) not null,
    join_date date not null,
    primary key(user_no)
);

create table blog(
    id varchar2(50),
    blog_title varchar2(200) not null,
    logo_file varchar2(200),
    primary key(id),
    constraint fk_blog foreign key(id) references users(id)
);

create table category (
    cate_no number,
    id varchar2(50),
    cate_name varchar2(200) not null,
    description varchar2(500),
    reg_date date not null,
    primary key(cate_no),
    constraint fk_category foreign key(id) references blog(id)
);

create table post (
    post_no number,
    cate_no number,
    post_title varchar2(300) not null,
    post_content varchar2(400),
    reg_date date not null,
    primary key(post_no),
    constraint fk_post foreign key(cate_no) references category(cate_no)
);

create table comments(
    cmt_no number,
    post_no number,
    user_no number,
    cmtContent varchar2(1000) not null,
    reg_date date not null,
    primary key(cmt_no),
    constraint fk_comments foreign key(post_no) references post(post_no),
    constraint fk_comments2 foreign key(user_no) references users(user_no)
);

create sequence seq_users_no
increment by 1 start with 1 nocache;

create sequence seq_category_no
increment by 1 start with 1 nocache;

create sequence seq_post_no
increment by 1 start with 1 nocache;

create sequence seq_comments_no
increment by 1 start with 1 nocache;

/******************
insert
*******************/
insert into users
values(seq_users_no.nextval, 'hw', 'heewon', '1234', sysdate);
insert into users
values(seq_users_no.nextval, 'yj', 'youjin', '1234', sysdate);
insert into users(user_no, id, user_name, password, join_date)
values(seq_users_no.nextval, 'jh', 'jihyun', '1234', sysdate);

insert into users(user_no, id, user_name, password, join_date)
values(seq_users_no.nextval, 'hs', 'haesung', '1234', sysdate);

/*******************
delete, drop
********************/
delete from users;

drop sequence seq_users_no;
/***************
id check
****************/
select *
from users
where id = 'hw';

commit;
