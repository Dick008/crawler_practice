package com.dick.task;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import com.dick.pojo.JobInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class JobProcessor implements PageProcessor {

	//解析数据
	public void process(Page page) {
		//1.获取到首页的所有超链接
		List<Selectable> list = page.getHtml().css("div#resultList div.el p.t1").links().nodes();
		
		//2.把超链接放入到队列中（包括首页的连接和下一页连接）
		
		//判断正在解析的是不是详情页
		if(list == null || list.size() == 0){
			//解析详情页面
			this.parsePage(page);
		}else {
			//爬取首页
			//把首页的超链接放入队列中，一会儿去爬
			List<String> bigPage = page.getHtml().css("div#resultList div.el p.t1").links().all();
			page.addTargetRequests(bigPage);
			
			//把下一页存入队列中(如果是第一页的话，只有一个 bk 元素)
			List<Selectable> nextPage = page.getHtml().css("div.p_in li.bk").links().nodes();
			if(nextPage.size() == 1) {
				page.addTargetRequest(nextPage.get(0).toString());
			}else {
				page.addTargetRequest(nextPage.get(1).toString());
			}
		}

	}

	//解析详情页
	private void parsePage(Page page) {
		//解析数据，创建JobInfo对象
		JobInfo job = new JobInfo();
		
		//1.设置职位名称
		String jobName = Jsoup.parse(page.getHtml().css("div.tHeader div.cn h1").toString()).text();
		job.setJobName(jobName);
		
		//2.设置公司名称
		String companyName = Jsoup.parse(page.getHtml().css("div.cn p.cname a").nodes().get(0).toString()).text();
		job.setCompanyName(companyName);
		
	    //公司介绍
		String companyInfo = Jsoup.parse(page.getHtml().css("div.tCompany_main div.tmsg").toString()).text();
		job.setCompanyInfo(companyInfo);
		
	    //工作地址
		String jobAddr = Jsoup.parse(page.getHtml().css("div.tCompany_main div.bmsg").nodes().get(1).toString()).text();
		job.setJobAddr(jobAddr.split("：")[1]);
		
		//工作内容
		String jobInfo = Jsoup.parse(page.getHtml().css("div.tCompany_main div.bmsg").nodes().get(0).toString()).text();
		job.setJobInfo(jobInfo);
		
		//职位链接地址
		job.setUrl(page.getUrl().toString());
		
	    //最低工资和最高工资
		String salary = Jsoup.parse(page.getHtml().css("div.cn strong").toString()).text();
		if(!"".equals(salary) && salary!=null) {
			String[] s = salary.substring(0, salary.indexOf("万")).split("-");
			if(salary.endsWith("月")) {
				double min = Double.valueOf(s[0]) * 10000;
				job.setSalaryMin((int)min);
				double max = Double.valueOf(s[1]) * 10000;
				job.setSalaryMax((int)max);
				
			}else if(salary.endsWith("年")) {
				double min = Double.valueOf(s[0]) * 10000 / 12;
				job.setSalaryMin((int)min);
				double max = Double.valueOf(s[1]) * 10000 / 12;
				job.setSalaryMax((int)max);
			}
		}

		
		
		//公司地址    北京-朝阳区  |  2年经验  |  本科  |  招1人  |  09-23发布
	    //发布时间
		String split = Jsoup.parse(page.getHtml().css("div.tHjob div.cn p.msg").toString()).text();
		String companyAddr = split.substring(0, split.indexOf("|"));
		
		int index = split.indexOf("发布");
		String time = split.substring(index-5, index);
		
		job.setCompanyAddr(companyAddr);
		job.setTime(time);
		
		//保存到 ResultItems 对象中
		page.putField("jobInfo", job);
	}

	//设置超时
	private Site site = Site.me()
			.setTimeOut(10*1000)
			.setRetrySleepTime(3000)
			.setRetryTimes(3);
	
	public Site getSite() {
		return site;
	}

}
