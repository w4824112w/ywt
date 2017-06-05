package gkyt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gkyt.dao.ExportLogMapper;
import gkyt.dao.PrisonMapper;
import gkyt.model.ExportLog;
import gkyt.pojo.PrisonDataDto;
import gkyt.service.ExportLogServiceI;
import gkyt.service.PrisonServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("prisonService")
public class PrisonServiceImpl implements PrisonServiceI {
	@Autowired
	private PrisonMapper prisonMapper;

	//@Transactional
	public List<Map<String, Object>> exportData_bak(PrisonDataDto data) {
		return prisonMapper.exportData_bak(data);
	}

	public List<Map<String, Object>> exportData(String sql) {
		Map<String ,String> op_sql=new HashMap<String ,String>();
		op_sql.put("sql", sql);
		return prisonMapper.exportData(op_sql);
	}

	
	
}
