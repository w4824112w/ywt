package gkyt.dao;

import gkyt.pojo.PrisonDataDto;

import java.util.List;
import java.util.Map;


public interface PrisonMapper {
	
	List<Map<String,Object>> exportData_bak(PrisonDataDto data);
	
	List<Map<String,Object>> exportData(Map<String,String> sql);
	
}