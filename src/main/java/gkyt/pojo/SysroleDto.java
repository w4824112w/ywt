package gkyt.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统角色dto
 * @author hk
 *
 */
public class SysroleDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String roleName;
	private String description;
	private String startTime;
	private String endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
