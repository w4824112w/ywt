package gkyt.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import gkyt.controller.api.SysmenuController;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DemoTask {
	/*private static final Logger log = Logger.getLogger(DemoTask.class);*/
	
/*	@Autowired
	private TbVisitorResidentRecordMapper visitorResidentRecordMapper;
	@Autowired
	private TbProvinceMapper tbProvinceMapper; */
	
/*	private static Properties prop=new Properties();*/
	
/*	static{
    	InputStream inputStream = DemoTask.class.getResourceAsStream("/config/interface.properties");;
    	try {
			prop.load(inputStream);
		} catch (IOException e) {
			log.error("加载interface.properties失败", e);
		}
	}*/
	
	/**
	 * 基础数据接口定时访问(每天上午凌晨2点拉取一次)
	 */
/*	@Scheduled(cron = "0 0 2 * * ?")
	public void recordHotelDataByDay() {
		
	}*/
	
	
	/**
	 * 基础数据接口定时访问(每间隔10秒拉取一次)
	 */
	@Scheduled(fixedDelay = 5000)
	public void test() {
		System.out.println("10定时跑批......");
	}
	
}
