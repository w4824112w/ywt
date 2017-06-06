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
	
	public Map<String,Object> saveOrUpdate(Sysmenu role);
	
	public PageResult<Sysmenu> findPage(PageBounds bounds,SysmenuDto dto);
	
	public List<Sysmenu> getAll(SysmenuDto dto);
	
	public int delete(String id);
	
}