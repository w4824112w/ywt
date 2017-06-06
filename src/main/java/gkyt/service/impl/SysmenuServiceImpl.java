package gkyt.service.impl;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.dao.SysmenuMapper;
import gkyt.model.Sysmenu;
import gkyt.pojo.SysmenuDto;
import gkyt.service.SysmenuServiceI;
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
@Service("sysmenuService")
public class SysmenuServiceImpl implements SysmenuServiceI {
	@Autowired
	private SysmenuMapper sysmenuMapper;

	public PageResult<Sysmenu> findPage(PageBounds bounds,SysmenuDto dto) {
	    PageList<Sysmenu> roles=sysmenuMapper.findPage(bounds,dto);
		return new PageResult<Sysmenu>(roles);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Sysmenu role) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		if(role.getId()!=null){
			role.setUpdatedAt(new Date());
			result = sysmenuMapper.update(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改用户成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改用户失败");
			}
		}else{
			role.setCreatedAt(new Date());
			result = sysmenuMapper.save(role);
			
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

	public Sysmenu getById(String id) {
		return sysmenuMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		sysmenuMapper.deleteMenuRole(id);
		return sysmenuMapper.delete(id);
	}

	public List<Sysmenu> getAll(SysmenuDto dto) {
		return sysmenuMapper.getAll(dto);
	}


}
