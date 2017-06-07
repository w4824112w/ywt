package gkyt.dao;

import java.util.List;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.RegisterReport;
import gkyt.pojo.RegisterReportDto;

public interface RegisterReportMapper {
	
	List<Map<String,Object>> dailyReport();
	
	PageList<RegisterReport> findPage(PageBounds bounds,RegisterReportDto dto);
	
	int save(RegisterReport report);
	
}