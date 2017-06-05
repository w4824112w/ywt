package gkyt.service.impl;

import gkyt.dao.SysuserMapper;
import gkyt.model.Sysuser;
import gkyt.service.SysuserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Sysuser login(String loginName, String loginPwd) {
		Sysuser obj = new Sysuser();
		obj.setLoginName(loginName);
		obj.setLoginPwd(loginPwd);
		
		Sysuser u = sysuserMapper.login(obj);
		return u;
	}


}
