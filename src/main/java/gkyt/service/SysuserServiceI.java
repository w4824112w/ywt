package gkyt.service;

import gkyt.model.Sysuser;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface SysuserServiceI {

	public Sysuser login(String name, String pwd);
}
