package gkyt.dao;

import java.util.List;

import gkyt.model.PrisonTerms;


public interface PrisonTermsMapper {
	
	void insert(PrisonTerms record);
	
	List<PrisonTerms> getPrisonTerms(PrisonTerms p);
}