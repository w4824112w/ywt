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
				retData.put("msg", "修改用户成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改用户失败");
			}
		}else{
			role.setCreatedAt(new Date());
			result = sysroleMapper.save(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增用户成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增用户失败");
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

	public List<Sysrole> getAll(SysroleDto dto) {
		return sysroleMapper.getAll(dto);
	}

	@Transactional
	public boolean saveRoleMenu(String[] menuIds, String roleId) {
		sysroleMapper.deleteRoleMenu(roleId);
		int ret=0;
		for(String menuId:menuIds){
			int count=sysroleMapper.addRoleMenu(menuId, roleId);
			ret=ret+count;
		}
		if(ret==menuIds.length){
			return true;
		}else{
			return false;
		}
	}


}
