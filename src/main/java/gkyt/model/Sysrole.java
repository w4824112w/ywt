package gkyt.model;

import java.util.List;

/**
 * 系统角色表
 * @author hk
 *
 */
public class Sysrole {

	private Integer roleId;
	private String roleName;
	private String roleDesc;
	private List<Rolemenu> menus;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Rolemenu> getMenus() {
		return menus;
	}
	public void setMenus(List<Rolemenu> menus) {
		this.menus = menus;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
