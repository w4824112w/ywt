package gkyt.service;

import java.util.Map;

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
	
	public Sysuser getById(String id);
	
	public Map<String,Object> saveOrUpdate(Sysuser user);
	
	public PageResult<Sysuser> findPage(PageBounds bounds,SysuserDto dto);
	
	public int delete(String id);
	
	public boolean delBatch(String[] ids);
	
}
