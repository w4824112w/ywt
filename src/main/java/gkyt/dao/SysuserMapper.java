package gkyt.dao;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;

public interface SysuserMapper {
	
	Sysuser login(SysuserDto dto);
	
	PageList<Sysuser> findPage(PageBounds bounds,SysuserDto dto);
}