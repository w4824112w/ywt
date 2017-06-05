package gkyt.service.impl;

import java.util.List;

import gkyt.dao.ExportLogMapper;
import gkyt.dao.PrisonTermsMapper;
import gkyt.dao.PrisonersMapper;
import gkyt.model.ExportLog;
import gkyt.model.PrisonTerms;
import gkyt.model.Prisoners;
import gkyt.service.ExportLogServiceI;
import gkyt.service.PrisonTermsServiceI;
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
@Service("prisonTermsService")
public class PrisonTermsServiceImpl implements PrisonTermsServiceI {
	@Autowired
	private PrisonTermsMapper prisonTermsMapper;

	//@Transactional
	public void insert(PrisonTerms record) {
		prisonTermsMapper.insert(record);
	}

	public boolean validate(PrisonTerms record) {
		List<PrisonTerms> rets=prisonTermsMapper.getPrisonTerms(record);
		if(rets!=null&&rets.size()>0){
			return true;
		}else{
			return false;
		}
	}


}
