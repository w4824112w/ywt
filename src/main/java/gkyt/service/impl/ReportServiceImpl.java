package gkyt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.commons.utils.DateUtil;
import gkyt.dao.OrderReportMapper;
import gkyt.dao.RegisterReportMapper;
import gkyt.model.RegisterReport;
import gkyt.model.OrderReport;
import gkyt.model.Sysuser;
import gkyt.pojo.OrderReportDto;
import gkyt.pojo.RegisterReportDto;
import gkyt.service.ReportServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("reportService")
public class ReportServiceImpl implements ReportServiceI {
	@Autowired
	private RegisterReportMapper registerReportMapper;
	
	@Autowired
	private OrderReportMapper orderReportMapper;

	@Transactional
	public void RegisterReport() {
		List<Map<String,Object>> reports= registerReportMapper.dailyReport();
		for(Map obj:reports){
			RegisterReport report=new RegisterReport();
			report.setJailId(Integer.parseInt(obj.get("jail_id").toString()));
			report.setNumber(Integer.parseInt(obj.get("number").toString()));
			report.setCreatedAt(DateUtil.getBeforeDay(new Date()));
			registerReportMapper.save(report);
		}
	}

	@Transactional
	public void OrderReport() {
		List<Map<String,Object>> reports= orderReportMapper.dailyReport();
		for(Map obj:reports){
			OrderReport report=new OrderReport();
			report.setJailId(Integer.parseInt(obj.get("jail_id").toString()));
			report.setNumber(Integer.parseInt(obj.get("number").toString()));
			report.setStatus(Integer.parseInt(obj.get("status").toString()));
			report.setCreatedAt(DateUtil.getBeforeDay(new Date()));
			orderReportMapper.save(report);
		}
	}

	public PageResult<RegisterReport> findRegisterReportPage(
			PageBounds bounds, RegisterReportDto dto) {
	    PageList<RegisterReport> reports=registerReportMapper.findPage(bounds,dto);
		return new PageResult<RegisterReport>(reports);
	}

	public PageResult<OrderReport> findOrderReportPage(
			PageBounds bounds, OrderReportDto dto) {
	    PageList<OrderReport> reports=orderReportMapper.findPage(bounds,dto);
		return new PageResult<OrderReport>(reports);
	}


}
