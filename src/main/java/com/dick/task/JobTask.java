package com.dick.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@Component
public class JobTask {

	private String url = "https://search.51job.com/list/010000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
	
	@Autowired
	private JobProcessor jobProcessor;
	
	@Autowired
	private DataPipeline dataPipeline;
	
	@Scheduled(fixedDelay=100*1000)
	public void job() {
		Spider.create(jobProcessor)  //jobProcessor 是解析类
				.addUrl(url)       //需要爬取的地址
				.addPipeline(dataPipeline)    //向数据库中保存，如果不设置，默认向控制台打印
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))   //使用布隆过滤器去重
				.thread(10)  
				.run();
	}
}
