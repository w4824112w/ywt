package gkyt.service.impl;

import gkyt.commons.paginator.domain.PageBounds;
import gkyt.commons.paginator.domain.PageList;
import gkyt.commons.paginator.domain.PageResult;
import gkyt.dao.SysuserMapper;
import gkyt.model.Sysuser;
import gkyt.pojo.SysuserDto;
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

	public Sysuser login(SysuserDto dto) {
		Sysuser u = sysuserMapper.login(dto);
		return u;
	}

	public PageResult<Sysuser> findPage(PageBounds bounds,SysuserDto dto) {
	    PageList<Sysuser> users=sysuserMapper.findPage(bounds,dto);
		return new PageResult<Sysuser>(users);
	}


}
