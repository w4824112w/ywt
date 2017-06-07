package gkyt.dao;

import java.util.List;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.Sysmenu;
import gkyt.pojo.SysmenuDto;

public interface SysmenuMapper {
	
	Sysmenu getById(String id);
	
	PageList<Sysmenu> findPage(PageBounds bounds,SysmenuDto dto);
	
	List<Sysmenu> getAll();
	
	List<Sysmenu> getSubmenu(SysmenuDto dto);
	
	List<Sysmenu> getOneLevelMenu(SysmenuDto dto);
	
	List<Sysmenu> getByRoleId(String roleId);
	
	int save(Sysmenu menu);
	
	int update(Sysmenu menu);
	
	int delete(String id);
	
	void deleteMenuRole(String menuId);
	
}