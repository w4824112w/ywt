package gkyt.service.impl;

import gkyt.dao.ExportLogMapper;
import gkyt.dao.PrisonersMapper;
import gkyt.model.ExportLog;
import gkyt.model.Prisoners;
import gkyt.service.ExportLogServiceI;
import gkyt.service.PrisonersServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service("prisonersService")
public class PrisonersServiceImpl implements PrisonersServiceI {
	@Autowired
	private PrisonersMapper prisonersMapper;

	@Transactional
	public int insert(Prisoners record) {
		return prisonersMapper.insert(record);
	}

	@Override
	public Prisoners validate(Prisoners record) {
		return prisonersMapper.getPrisoner(record);
	}


}
