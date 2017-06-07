package gkyt.task;

import gkyt.service.ReportServiceI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderDataTask {
	private static final Logger log = Logger.getLogger(OrderDataTask.class);
	
    @Autowired
    private ReportServiceI reportService;
	
	/**
	 * 订单每天统计接口定时调度(每天上午凌晨2点拉取一次)
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void recordHotelDataByDay() {
		log.info("开始记录每天订单数");
		long startTime = System.currentTimeMillis();
		
		try {
			reportService.OrderReport();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("插入数据库失败......", e);
		} 
		
		long endTime = System.currentTimeMillis();
		log.info("记录每天订单数完成，耗时：" + (endTime-startTime) + " ms");
	}
	
	
}
