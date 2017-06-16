package gkyt.service;

import gkyt.dao.FamilyCustomMapper;
import gkyt.dao.FamilyMapper;
import gkyt.model.Family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/9.
 */
@Service("familyService")
public class FamilyService {
    @Autowired
    private FamilyCustomMapper familyCustomMapper;

    public List<Family> getFamilies(Integer jailId, String prisonerName, String phone, String name, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return familyCustomMapper.getFamilies(params);
    }

    public int countFamilies(Integer jailId, String prisonerName, String phone, String name, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return familyCustomMapper.countFamilies(params);
    }
}
