package gkyt.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 监狱数据导出操作员
 * @author hk
 *
 */
public class ExportLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;	//用户id
	private Integer jailId;	//监狱id
	private Integer userId;	//用户姓名
	private String exportInfo;	//用户姓名
	private Date createTime;	//创建时间
	
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getExportInfo() {
		return exportInfo;
	}
	public void setExportInfo(String exportInfo) {
		this.exportInfo = exportInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}