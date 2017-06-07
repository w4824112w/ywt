package gkyt.service;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.OrderReport;
import gkyt.model.RegisterReport;
import gkyt.pojo.OrderReportDto;
import gkyt.pojo.RegisterReportDto;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface ReportServiceI {

	public void RegisterReport();
	
	public void OrderReport();
	
	public PageResult<RegisterReport> findRegisterReportPage(PageBounds bounds,RegisterReportDto dto);
	
	public PageResult<OrderReport> findOrderReportPage(PageBounds bounds,OrderReportDto dto);
	
}
