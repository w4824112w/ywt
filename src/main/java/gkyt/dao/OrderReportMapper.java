package gkyt.dao;

import java.util.List;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.OrderReport;
import gkyt.pojo.OrderReportDto;

public interface OrderReportMapper {
	
	List<Map<String,Object>> dailyReport();
	
	PageList<OrderReport> findPage(PageBounds bounds,OrderReportDto dto);
	
	int save(OrderReport report);
	
}