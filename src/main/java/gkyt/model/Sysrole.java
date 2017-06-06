package gkyt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统角色表
 * @author hk
 *
 */
public class Sysrole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roleName;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private List<Rolemenu> menus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Rolemenu> getMenus() {
		return menus;
	}
	public void setMenus(List<Rolemenu> menus) {
		this.menus = menus;
	}
	
	
}
