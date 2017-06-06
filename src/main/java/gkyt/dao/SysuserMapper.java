package gkyt.dao;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;

public interface SysuserMapper {
	
	Sysuser login(SysuserDto dto);
	
	int checkLoginName(String loginName);
	
	Sysuser getById(String id);
	
	PageList<Sysuser> findPage(PageBounds bounds,SysuserDto dto);
	
	int save(Sysuser user);
	
	int update(Sysuser user);
	
	int delete(String id);
	
}