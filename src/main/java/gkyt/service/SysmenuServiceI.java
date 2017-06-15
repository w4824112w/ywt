package gkyt.service;

import java.util.List;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysmenu;
import gkyt.pojo.SysmenuDto;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface SysmenuServiceI {

	public Sysmenu getById(String id);
	
	public Map<String,Object> saveOrUpdate(Sysmenu menu);
	
	public PageResult<Sysmenu> findPage(PageBounds bounds,SysmenuDto dto);
	
	public List<Sysmenu> getAll();
	
	public List<Sysmenu> getOneLevelMenu();
	
	public List<Sysmenu> getByRoleId(String roleId);
	
	public int delete(String id);
	
	public boolean delBatch(String[] ids);
}
