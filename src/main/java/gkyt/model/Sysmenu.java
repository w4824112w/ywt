package gkyt.model;

import java.io.Serializable;
import java.util.List;


/**
 * 系统功能菜单
 * @author hk
 *
 */
public class Sysmenu  {
	
	private Integer id;
	private String menuName;
	private String menuPath;
	private Integer menuLevel;
	private Integer parentId;
	private String menuIcon;
	private Integer menuShow;
	private String menuOrder;
	private String remark;
	private Integer btnMenuType;
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
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public Integer getMenuShow() {
		return menuShow;
	}
	public void setMenuShow(Integer menuShow) {
		this.menuShow = menuShow;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBtnMenuType() {
		return btnMenuType;
	}
	public void setBtnMenuType(Integer btnMenuType) {
		this.btnMenuType = btnMenuType;
	}
	public List<Sysmenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Sysmenu> subMenus) {
		this.subMenus = subMenus;
	}
	
	
}
