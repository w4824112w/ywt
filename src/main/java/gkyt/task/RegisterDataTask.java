package gkyt.task;

import gkyt.service.ReportServiceI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RegisterDataTask {
	private static final Logger log = Logger.getLogger(RegisterDataTask.class);
	
    @Autowired
    private ReportServiceI reportService;
	
	/**
	 * 家属注册每天统计接口定时调度(每天上午凌晨2点拉取一次)
	 */
	@Scheduled(cron = "0 0 2 * * ?")
//    @Scheduled(fixedDelay = 30000)
	public void recordHotelDataByDay() {
		log.info("开始记录犯人家属每天注册人数");
		long startTime = System.currentTimeMillis();
		
		try {
			reportService.RegisterReport();
		//	reportService.OrderReport();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("插入数据库失败......", e);
		} 
		
		long endTime = System.currentTimeMillis();
		log.info("记录犯人家属每天注册人数完成，耗时：" + (endTime-startTime) + " ms");
	}
	
	
}
