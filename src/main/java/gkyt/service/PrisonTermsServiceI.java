package gkyt.service;

import gkyt.model.PrisonTerms;
import gkyt.model.Prisoners;

/**
 * 本地的
 * @author Administrator
 *
 */
public interface PrisonTermsServiceI {

	public 	void insert(PrisonTerms record);

	public boolean validate(PrisonTerms record);
}
