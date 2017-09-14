package com.shuyu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "score")
public class Score implements Serializable{
	private static final long serialVersionUID = 8127035730921338189L;
	
	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false, name = "STUDENTID") // ����˵һ�£���ʹ��ָ�����ݿ��е�ʱ��ʹ��Сд�᲻�����ã��޸�Ϊ��д�������ˡ���֪��Ϊ�Σ��������һ������Ŀ��Գ����¡�
	private int stuId;

	@Column(nullable = false, name = "SUBJECTNAME")
	private String subjectName;

	@Column(nullable = false, name = "SCORE")
	private float score;

	@Column(nullable = false, name = "EXAMTIME")
	private Date examTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}
	

}
