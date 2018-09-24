package com.dick.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dick.pojo.JobInfo;
import com.dick.service.JobInfoService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class DataPipeline implements Pipeline{
	
	@Autowired
	private JobInfoService jobInfoService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		JobInfo jobinfo = resultItems.get("jobInfo");
		
		if(jobinfo != null) {
			jobInfoService.save(jobinfo);
		}
	}

}
