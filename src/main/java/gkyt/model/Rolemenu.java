package gkyt.model;

import java.io.Serializable;

/**
 * 角色菜单权限
 * @author hk
 *
 */
public class Rolemenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String menuId;
	private String roleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
