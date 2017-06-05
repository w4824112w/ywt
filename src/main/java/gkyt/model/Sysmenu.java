package gkyt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 系统功能菜单
 * @author hk
 *
 */
public class Sysmenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String menuName;
	private String menuPath;
	private Integer menuType;
	private Integer menuOrder;
	private Integer pid;
	private Date createdAt;
	private Date updatedAt;
	private List<Sysmenu> subMenus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public List<Sysmenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Sysmenu> subMenus) {
		this.subMenus = subMenus;
	}
	
	
}
