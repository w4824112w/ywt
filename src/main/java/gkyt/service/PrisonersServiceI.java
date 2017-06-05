package gkyt.service;

import gkyt.model.ExportLog;
import gkyt.model.Prisoners;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface PrisonersServiceI {

	public 	int insert(Prisoners record);
	
	public Prisoners validate(Prisoners record);
}
