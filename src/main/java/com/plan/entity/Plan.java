package com.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="PLAN_MASTER")
public class Plan {
	@Id
	@GeneratedValue
	@Column(name="PLAN_ID")
  private Integer planId;
	@Column(name="PLAN_NAME")
  private String planName;
	@Column(name="START_DATE")
  private Date startDate;
	@Column(name="END_DATE")
  private Date endDate;
	@Column(name="CATEGORY_ID")
  private Integer categoryId;
	@Column(name="CREATED_BY")
  private String createdBy;
	@Column(name="UPDATED_BY")
  private String updatedBy;
	@Column(name="CREATED_DATE", updatable = false)
	@CreationTimestamp
  private Date createdDate;
	@Column(name="UPDATED_DATE", insertable = false)
	@UpdateTimestamp
  private Date updatedDate;
	@Column(name="ACTIVE_SW")
  private  String activeSw;
  
}
