package com.sjmp.dao;

import com.sjmp.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author: sjmp1573
 * @date: 2020/10/2 11:44
 * @description:
 */

// JpaSpecificationExecutor<Blog> 实现组合动态的查询
public interface BlogRepository extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog>{

    @Query("select b from Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

//  select * from t_blog where title like '%内容%'
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog>  findByQuery(String query,Pageable pageable);

//    更新浏览次数 view
    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y')as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y')=?1")
    List<Blog> findByYear(String year);
}
