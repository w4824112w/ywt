package gkyt.model;

/**
 * 角色菜单权限
 * @author hk
 *
 */
public class Rolemenu {
	
	private Integer id;
	private String menuId;
	private String roleId;
	private Integer menuLevel;
	
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
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	
	
}
