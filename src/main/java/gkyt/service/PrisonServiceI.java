package gkyt.service;

import gkyt.pojo.PrisonDataDto;

import java.util.List;
import java.util.Map;


/**
 * 本地的
 * @author Administrator
 *
 */
public interface PrisonServiceI {

	public List<Map<String,Object>> exportData_bak(PrisonDataDto data);
	
	public List<Map<String, Object>> exportData(String sql);
}
