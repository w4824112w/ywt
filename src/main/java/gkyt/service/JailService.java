package gkyt.service;

import com.alibaba.druid.util.StringUtils;

import gkyt.dao.JailCustomMapper;
import gkyt.dao.JailMapper;
import gkyt.model.Jail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/8.
 */
@Service("jailService")
public class JailService {
    @Autowired
    private JailMapper jailMapper;
    @Autowired
    private JailCustomMapper jailCustomMapper;

    public int addJail(String prisonName, String zipCode){
        if (StringUtils.isEmpty(prisonName)){
            return 0;
        }
        Jail jail = new Jail();
        jail.setTitle(prisonName);
        jail.setZipcode(zipCode);
        jail.setCreatedAt(new Date());
        jail.setUpdatedAt(new Date());
        jailMapper.insertSelective(jail);
        return jail.getId();
    }

    public Jail getJailById(Integer jailId) {
        if (jailId == 0){
            return null;
        }

        return jailMapper.selectByPrimaryKey(jailId);
    }

    public List<Jail> getJails(String title, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return jailCustomMapper.getJails(params);
    }

    public int countJails(String title, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return jailCustomMapper.countJails(params);
    }
}
