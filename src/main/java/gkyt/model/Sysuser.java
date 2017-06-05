package gkyt.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户表
 * @author hk
 *
 */
public class Sysuser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String realName;
	private String loginName;
	private String loginPwd;
	private String email;
	private Integer status;
	private Integer jailId;
	private Integer roleId;
	private Integer isDelete;
	private Date createdAt;
	private Date updatedAt;
	private Sysrole role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getJailId() {
		return jailId;
	}
	public void setJailId(Integer jailId) {
		this.jailId = jailId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
	public Sysrole getRole() {
		return role;
	}
	public void setRole(Sysrole role) {
		this.role = role;
	}


}
