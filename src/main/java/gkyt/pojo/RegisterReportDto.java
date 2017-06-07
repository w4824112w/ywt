package gkyt.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 家属注册统计报表dto
 * @author hk
 *
 */
public class RegisterReportDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String jailId;
	private String startTime;
	private String endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJailId() {
		return jailId;
	}
	public void setJailId(String jailId) {
		this.jailId = jailId;
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
