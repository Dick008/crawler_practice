package com.dick.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_info")
public class JobInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    //公司名称
    private String companyName; 
    //公司地址
    private String companyAddr; 
    //公司介绍
    private String companyInfo; 
    //职位名称
    private String jobName; 
    //工作地址
    private String jobAddr; 
    //工作内容
    private String jobInfo; 
    //最低工资
    private Integer salaryMin; 
    //最高工资
    private Integer salaryMax; 
    //职位链接地址
    private String url; 
    //发布时间
    private String time;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobAddr() {
		return jobAddr;
	}
	public void setJobAddr(String jobAddr) {
		this.jobAddr = jobAddr;
	}
	public String getJobInfo() {
		return jobInfo;
	}
	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}
	public Integer getSalaryMin() {
		return salaryMin;
	}
	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}
	public Integer getSalaryMax() {
		return salaryMax;
	}
	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
