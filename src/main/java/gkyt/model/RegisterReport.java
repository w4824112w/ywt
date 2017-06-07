package gkyt.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 家属注册统计报表
 * @author hk
 *
 */
public class RegisterReport implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer jailId;
	private Integer number;
	private Date createdAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJailId() {
		return jailId;
	}
	public void setJailId(Integer jailId) {
		this.jailId = jailId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


}
