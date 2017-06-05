package gkyt.service;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface SysuserServiceI {

	public Sysuser login(SysuserDto dto);
	
	public PageResult<Sysuser> findPage(PageBounds bounds,SysuserDto dto);
}
