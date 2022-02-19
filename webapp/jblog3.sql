select post_no as postNo,
       cate_no as cateNo,
       post_title as postTitle,
       post_content as postContent,
       reg_date as regDate
from post
where cate_no in (select cate_no
                  from category
                  where id = #{userId})
order by reg_date desc;

select rt.post_no postNo,
       rt.cate_no cateNo,
       rt.post_title postTitle,
       rt.post_content postContent,
       rt.reg_date regDate
from (select rownum rn,
             ot.post_no,
             ot.cate_no,
             ot.post_title,
             ot.post_content,
             ot.reg_date
      from (select post_no,
                   cate_no,
                   post_title,
                   post_content,
                   reg_date
            from post
            where cate_no in (select cate_no
                              from category
                              where id = 'mk')
            order by reg_date desc
           ) ot
    ) rt
where rt.rn between to_number(1) and to_number(2); 

select rownum rn,
             post_no,
             cate_no,
             post_title,
             post_content
             reg_date
  from (select post_no,
               cate_no,
               post_title,
               post_content,
               reg_date
        from post
        where cate_no in (select cate_no
                          from category
                          where id = 'mk')
        order by reg_date desc
       );
       
select post_no postNo,
       cate_no cateNo,
       post_title postTitle,
       post_content postContent,
       to_char(reg_date, 'YYYY/MM/DD') regDate
from post
where cate_no in (select cate_no
                  from category
                  where id = 'mk')
and reg_date = (select max(po.reg_date)
                from post po, category ca
                where po.cate_no = ca.cate_no
                and ca.id = 'mk');
                
select count(post_no)
from post
where cate_no in (select cate_no
                  from category
                  where id = 'hw');