package com.dick.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.dick.dao.JobInfoDao;
import com.dick.pojo.JobInfo;

@Service
@Transactional
public class JobInfoServiceImpl implements JobInfoService {

	@Autowired
	private JobInfoDao JobInfoDao;
	
	
	public List<JobInfo> findJobInfo(JobInfo jobInfo) {
		Example<JobInfo> example = Example.of(jobInfo);
		
		return JobInfoDao.findAll(example);
	}

	public void save(JobInfo jobInfo) {
		//如果该数据已经保存过，就不再保存
		JobInfo j = new JobInfo();
		j.setUrl(jobInfo.getUrl());
		j.setTime(jobInfo.getTime());
		
		List<JobInfo> list = this.findJobInfo(j);
		
		if(list.size() == 0) {
			//新增数据
			JobInfoDao.saveAndFlush(jobInfo);
		}
	}

}
