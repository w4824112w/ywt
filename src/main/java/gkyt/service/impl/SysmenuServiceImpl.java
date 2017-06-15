package gkyt.service.impl;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.dao.SysmenuMapper;
import gkyt.model.Sysmenu;
import gkyt.pojo.SysmenuDto;
import gkyt.service.SysmenuServiceI;

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
@Service("sysmenuService")
public class SysmenuServiceImpl implements SysmenuServiceI {
	@Autowired
	private SysmenuMapper sysmenuMapper;

	public PageResult<Sysmenu> findPage(PageBounds bounds,SysmenuDto dto) {
	    PageList<Sysmenu> menus=sysmenuMapper.findPage(bounds,dto);
		return new PageResult<Sysmenu>(menus);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Sysmenu menu) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		if(menu.getId()!=null){
			menu.setUpdatedAt(new Date());
			result = sysmenuMapper.update(menu);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改菜单成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改菜单失败");
			}
		}else{
			menu.setCreatedAt(new Date());
			result = sysmenuMapper.save(menu);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增菜单成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增菜单失败");
			}
		}
		
		return retData;
	}

	public Sysmenu getById(String id) {
		Sysmenu menu=sysmenuMapper.getById(id);
		SysmenuDto dto=new SysmenuDto();
		dto.setId(id);
		menu.setSubMenus(sysmenuMapper.getSubmenu(dto));
		return menu;
	}

	@Transactional
	public int delete(String id) {
		sysmenuMapper.deleteMenuRole(id);
		return sysmenuMapper.delete(id);
	}

	public List<Sysmenu> getAll() {
		List<Sysmenu> menus = sysmenuMapper.getAll();
		for(Sysmenu menu:menus){
			SysmenuDto dto=new SysmenuDto();
			dto.setId(menu.getId().toString());
			menu.setSubMenus(sysmenuMapper.getSubmenu(dto));
		}
		return menus;
	}

	public List<Sysmenu> getOneLevelMenu() {
		return sysmenuMapper.getOneLevelMenu();
	}

	public List<Sysmenu> getByRoleId(String roleId) {
		return sysmenuMapper.getByRoleId(roleId);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret=0;
		for(String id:ids){
			sysmenuMapper.deleteMenuRole(id);
			int count=sysmenuMapper.delete(id);
			ret=ret+count;
		}
		if(ret==ids.length){
			return true;
		}else{
			return false;
		}
	}


}
