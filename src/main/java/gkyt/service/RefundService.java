package gkyt.service;

import com.alibaba.dubbo.config.annotation.Service;
import gkyt.dao.DrawBackCustomMapper;
import gkyt.model.Drawback;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/12.
 */
@Service
public class RefundService {
    @Autowired
    private DrawBackCustomMapper drawBackCustomMapper;
    
    public List<Drawback> getDrawbacks(Integer jailId, String prisonerName, String phone, String name, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return drawBackCustomMapper.getDrawbacks(params);
    }

    public int countDrawbacks(Integer jailId, String prisonerName, String phone, String name, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return drawBackCustomMapper.countDrawbacks(params);
    }
}
