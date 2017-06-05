package gkyt.service.impl;

import gkyt.dao.ExportLogMapper;
import gkyt.model.ExportLog;
import gkyt.service.ExportLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("exportLogService")
public class ExportLogServiceImpl implements ExportLogServiceI {
	@Autowired
	private ExportLogMapper exportLogMapper;

	//@Transactional
	public int insert(ExportLog record) {
		int ret=exportLogMapper.insert(record);
		return ret;
	}


}
