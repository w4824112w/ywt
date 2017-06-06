package gkyt.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统功能菜单dto
 * @author hk
 *
 */
public class SysmenuDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String menuName;
	private Integer menuType;
	private String startTime;
	private String endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
