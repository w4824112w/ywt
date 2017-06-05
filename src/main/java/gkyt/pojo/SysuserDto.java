package gkyt.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户表
 * @author hk
 *
 */
public class SysuserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String loginName;
	private String loginPwd;
	private String jailId;
	private String roleId;
	private String status;
	private String startTime;
	private String endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getJailId() {
		return jailId;
	}
	public void setJailId(String jailId) {
		this.jailId = jailId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
