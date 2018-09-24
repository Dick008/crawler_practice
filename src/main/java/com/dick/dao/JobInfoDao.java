package com.dick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dick.pojo.JobInfo;

public interface JobInfoDao extends JpaRepository<JobInfo, Long>{

}
