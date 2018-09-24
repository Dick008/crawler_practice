package com.dick.service;

import java.util.List;

import com.dick.pojo.JobInfo;

public interface JobInfoService {
	
	//查询符合条件的
	public List<JobInfo> findJobInfo(JobInfo jobInfo);
	
	//保存数据
	public void save(JobInfo jobInfo);
}
