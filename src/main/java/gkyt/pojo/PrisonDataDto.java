package gkyt.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据导出
 * @author hk
 *
 */
public class PrisonDataDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer jailId;	//监狱id
	private String startTime;	//开始时间
	private String endTime;	//结束时间
	private String queryField;	//查询导出字段
	private String exportField;	//查询导出字段
	public Integer getJailId() {
		return jailId;
	}
	public void setJailId(Integer jailId) {
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
	public String getQueryField() {
		return queryField;
	}
	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}
	public String getExportField() {
		return exportField;
	}
	public void setExportField(String exportField) {
		this.exportField = exportField;
	}
	

	
	
}