package gkyt.service;

import java.util.List;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.model.Sysrole;
import gkyt.pojo.SysroleDto;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface SysroleServiceI {

	public Sysrole getById(String id);
	
	public Map<String,Object> saveOrUpdate(Sysrole role);
	
	public PageResult<Sysrole> findPage(PageBounds bounds,SysroleDto dto);
	
	public List<Sysrole> getAll();
	
	public int delete(String id);
	
	public boolean saveRoleMenu(String[] menuIds,String roleId);
	
	public boolean delBatch(String[] ids);
	
}
