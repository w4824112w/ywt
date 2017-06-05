package gkyt.model;

import java.io.Serializable;
import java.util.Date;

public class Prisoners implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;	//用户id 
	private String prisonerNumber;	//犯人编号
	private String name;	//犯人名称
	private String gender;	//犯人性别
	private String crimes;	//犯罪说明
	private Integer jailId;	//监狱id
	private Date prisonTermStartedAt;	//坐牢开始时间
	private Date prisonTermEndedAt;	//坐牢结束时间
	private Date createdAt;	//创建日期
	private Date updatedAt;	//更新日期
	private String prisonArea;	//监区名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrisonerNumber() {
		return prisonerNumber;
	}
	public void setPrisonerNumber(String prisonerNumber) {
		this.prisonerNumber = prisonerNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCrimes() {
		return crimes;
	}
	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}
	public Integer getJailId() {
		return jailId;
	}
	public void setJailId(Integer jailId) {
		this.jailId = jailId;
	}
	public Date getPrisonTermStartedAt() {
		return prisonTermStartedAt;
	}
	public void setPrisonTermStartedAt(Date prisonTermStartedAt) {
		this.prisonTermStartedAt = prisonTermStartedAt;
	}
	public Date getPrisonTermEndedAt() {
		return prisonTermEndedAt;
	}
	public void setPrisonTermEndedAt(Date prisonTermEndedAt) {
		this.prisonTermEndedAt = prisonTermEndedAt;
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
	public String getPrisonArea() {
		return prisonArea;
	}
	public void setPrisonArea(String prisonArea) {
		this.prisonArea = prisonArea;
	}
	

	


}
