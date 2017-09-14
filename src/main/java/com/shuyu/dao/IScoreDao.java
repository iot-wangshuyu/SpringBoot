package com.shuyu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shuyu.entity.Score;

/**
 * Ȼ�����Ǽ̳п��Ϊ�����ṩ�õĽӿ� Repository �� CrudRepository ��CrudRepository �̳��� Repository��������Ϊ�����ṩ�˶����ݿ�Ļ�����������
 * ���������ʹ�����޸ġ�������ɾ�������������Ҫ�ڽӿ�������߶�Ӧ�ķ���������� @Transactional ע�⣬������׳��쳣
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
