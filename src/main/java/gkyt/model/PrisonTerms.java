package gkyt.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 刑期变动表
 * @author hk
 *
 */
public class PrisonTerms implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;	
	private Integer prisonerId;	//犯人id
	private Date termStart;	//坐牢开始时间
	private Date termFinish;	//坐牢结束时间
	private Date createdAt;	//创建日期
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrisonerId() {
		return prisonerId;
	}
	public void setPrisonerId(Integer prisonerId) {
		this.prisonerId = prisonerId;
	}
	public Date getTermStart() {
		return termStart;
	}
	public void setTermStart(Date termStart) {
		this.termStart = termStart;
	}
	public Date getTermFinish() {
		return termFinish;
	}
	public void setTermFinish(Date termFinish) {
		this.termFinish = termFinish;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

	
}
