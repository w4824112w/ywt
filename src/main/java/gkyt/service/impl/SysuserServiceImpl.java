package gkyt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.dao.SysuserMapper;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;
import gkyt.service.SysuserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("sysuserService")
public class SysuserServiceImpl implements SysuserServiceI {
	@Autowired
	private SysuserMapper sysuserMapper;

	public Sysuser login(SysuserDto dto) {
		Sysuser u = sysuserMapper.login(dto);
		return u;
	}

	public PageResult<Sysuser> findPage(PageBounds bounds,SysuserDto dto) {
	    PageList<Sysuser> users=sysuserMapper.findPage(bounds,dto);
		return new PageResult<Sysuser>(users);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Sysuser user) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		//默认密码，md5加密
	//	o.setLoginPwd(MD5.MD5Hash("123456"));
		
		if(user.getId()!=null){
			user.setUpdatedAt(new Date());
			result = sysuserMapper.update(user);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改用户成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改用户失败");
				return retData;
			}
		}else{
			int count=sysuserMapper.checkLoginName(user.getLoginName());
			if(count>0){
				retData.put("code", "1");
				retData.put("msg", "账户名称已经存在");
				return retData;
			}
			
			user.setCreatedAt(new Date());
			result = sysuserMapper.save(user);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增用户成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增用户失败");
				return retData;
			}
		}
		
		
	}

	public Sysuser getById(String id) {
		return sysuserMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		return sysuserMapper.delete(id);
	}


}
