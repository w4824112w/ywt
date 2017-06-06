package gkyt.dao;

import java.util.List;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.model.Sysrole;
import gkyt.pojo.SysroleDto;

public interface SysroleMapper {
	
	Sysrole getById(String id);
	
	PageList<Sysrole> findPage(PageBounds bounds,SysroleDto dto);
	
	List<Sysrole> getAll(SysroleDto dto);
	
	int save(Sysrole role);
	
	int update(Sysrole role);
	
	int delete(String id);
	
	void deleteRoleMenu(String roleId);
	
}