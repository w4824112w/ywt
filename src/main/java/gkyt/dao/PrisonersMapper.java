package gkyt.dao;

import gkyt.model.Prisoners;


public interface PrisonersMapper {
	
	int insert(Prisoners record);
	
	Prisoners getPrisoner(Prisoners p);
	
//	List<Map<String,Object>> ImportData(Map<String,String> sql);
}