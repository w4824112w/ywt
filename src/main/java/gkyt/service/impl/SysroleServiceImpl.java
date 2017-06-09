package gkyt.service.impl;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.dao.SysroleMapper;
import gkyt.model.Sysrole;
import gkyt.pojo.SysroleDto;
import gkyt.service.SysroleServiceI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("sysroleService")
public class SysroleServiceImpl implements SysroleServiceI {
	@Autowired
	private SysroleMapper sysroleMapper;

	public PageResult<Sysrole> findPage(PageBounds bounds,SysroleDto dto) {
	    PageList<Sysrole> roles=sysroleMapper.findPage(bounds,dto);
		return new PageResult<Sysrole>(roles);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Sysrole role) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		if(role.getId()!=null){
			role.setUpdatedAt(new Date());
			result = sysroleMapper.update(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改角色成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改角色失败");
			}
		}else{
			role.setCreatedAt(new Date());
			result = sysroleMapper.save(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增角色成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增角色失败");
			}
		}
		
		return retData;
	}

	public Sysrole getById(String id) {
		return sysroleMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		sysroleMapper.deleteRoleMenu(id);
		return sysroleMapper.delete(id);
	}

	public List<Sysrole> getAll() {
		return sysroleMapper.getAll();
	}

	@Transactional
	public boolean saveRoleMenu(String[] menuIds, String roleId) {
		sysroleMapper.deleteRoleMenu(roleId);
		int ret=0;
		for(String menuId:menuIds){
			Map<String,Object> obj=new HashMap<String,Object>();
			obj.put("menuId", menuId);
			obj.put("roleId", roleId);
			int count=sysroleMapper.addRoleMenu(obj);
			ret=ret+count;
		}
		if(ret==menuIds.length){
			return true;
		}else{
			return false;
		}
	}


}
