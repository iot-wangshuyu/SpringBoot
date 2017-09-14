package com.shuyu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shuyu.entity.Score;

/**
 * 然后我们继承框架为我们提供好的接口 Repository 或 CrudRepository （CrudRepository 继承自 Repository），其中为我们提供了对数据库的基本操作方法
 * 如果你其中使用了修改、新增、删除操作，则必须要在接口上面或者对应的方法上面添加 @Transactional 注解，否则会抛出异常
 * @author Shuyu.Wang
 *
 */
public interface  IScoreDao extends CrudRepository<Score, Integer>{
	
	
	@Transactional
    @Modifying
    @Query("update Score t set t.score = :score where t.id = :id")
    int updateScoreById(@Param("score") float score, @Param("id") int id);

    @Query("select t from Score t ")
    List<Score> getList();


}
