/***********************
insert blog
************************/
insert into blog(id, blog_title)
values('mk', '재수' || '의 블로그 입니다.');

/***********************************/
select blog.id, 
       blog_title as blogTitle, 
       logo_file as logoFile,
       user_name as userName
from blog, users
where blog.id = users.id
and blog.id = 'mk';

/*****************
insert category, post
******************/
insert into category(cate_no, id, cate_name, description, reg_date)
values(seq_category_no.nextval, 'mk', 'cate-1', 'cate-desc', sysdate);
insert into category(cate_no, id, cate_name, description, reg_date)
values(seq_category_no.nextval, 'mk', 'cate-2', 'cate-desc2', sysdate);
insert into category(cate_no, id, cate_name, description, reg_date)
values(seq_category_no.nextval, 'mk', 'cate-3', 'cate-desc3', sysdate);

insert into post(post_no, cate_no, post_title, post_content, reg_date)
values(seq_post_no.nextval, 1, '밥 잘먹는 법', '운동해라', sysdate);
insert into post(post_no, cate_no, post_title, post_content, reg_date)
values(seq_post_no.nextval, 2, '잘 자는 법', '일찍 일어나라', sysdate);
insert into post(post_no, cate_no, post_title, post_content, reg_date)
values(seq_post_no.nextval, 2, '행복해지는법', '많이 웃어라', sysdate);

insert into post(post_no, cate_no, post_title, post_content, reg_date)
values(seq_post_no.nextval, 3, '미국영화', '미국 영화 소개', sysdate);

/***********************************/
select cate_no cateNo,
       id,
       cate_name cateName,
       description,
       reg_date regDate
from category
where id = 'mk';

/***********************************/
SELECT USER_NAME userName, 
       USER_NO userNo,
       blog.blog_title blogTitle,
       users.id
FROM USERS, BLOG
WHERE USERS.ID = BLOG.ID;

/***********************************/
select ca.cate_no,
       cate_name,
       description,
       cnt
from category ca, (select cate_no, count(post_no) cnt
                from post
                group by cate_no) ct
where ca.cate_no = ct.cate_no
and ca.id = 'th';

/***********************************/
select cate_name,
       cate_no,
       description,
       postCnt
from category
where cate_no = 1;

/***********************************/
select cate_name
       , cate_no
       , description
       , (select count(post_no) 
          from post
          group by cate_no
          having cate_no = 1) postCnt
from category
where cate_no = 12;

/***********************************/
select cate.cate_no as cateNo,
       cate_name as cateName,
       postCnt,
       description
from category cate, (select cate_no, count(post_no) postCnt
                    from post 
                    group by cate_no) cnt
where cate.cate_no = cnt.cate_no(+)
and cate.cate_no = 10;

/***********************************/
select CONSTRAINT_NAME, TABLE_NAME, R_CONSTRAINT_NAME
from user_constraints
where CONSTRAINT_NAME = 'JBLOG.FK_POST';
