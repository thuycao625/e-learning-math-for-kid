package com.codeenginestudio.elearning.dao.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assessments")
public class AssessmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long assessmentid;

	@Column
	private String assessmentname;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "classid", referencedColumnName = "classid")
	private ClassEntity classForeign;

	@Column
	private LocalDate startdate;

	@Column
	private LocalDate expireddate;

	@Column
	private Boolean status;

	public AssessmentEntity() {
		super();
	}

	public AssessmentEntity(Long assessmentid, String assessmentname, LocalDate startdate, LocalDate expireddate,
			Boolean status) {
		super();
		this.assessmentid = assessmentid;
		this.assessmentname = assessmentname;
		this.startdate = startdate;
		this.expireddate = expireddate;
		this.status = status;
	}

	public Long getAssessmentid() {
		return assessmentid;
	}

	public void setAssessmentid(Long assessmentid) {
		this.assessmentid = assessmentid;
	}

	public String getAssessmentname() {
		return assessmentname;
	}

	public void setAssessmentname(String assessmentname) {
		this.assessmentname = assessmentname;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getExpireddate() {
		return expireddate;
	}

	public void setExpireddate(LocalDate expireddate) {
		this.expireddate = expireddate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ClassEntity getClassForeign() {
		return classForeign;
	}

	public void setClassForeign(ClassEntity classForeign) {
		this.classForeign = classForeign;
	}

}
