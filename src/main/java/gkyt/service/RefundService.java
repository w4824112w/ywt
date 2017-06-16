package gkyt.service;

import gkyt.dao.DrawBackCustomMapper;
import gkyt.dao.DrawbackLogMapper;
import gkyt.dao.DrawbackMapper;
import gkyt.model.Drawback;
import gkyt.model.DrawbackLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyt38 on 2017/6/12.
 */
@Service("refundService")
public class RefundService {
    @Autowired
    private DrawBackCustomMapper drawBackCustomMapper;
    @Autowired
    private DrawbackMapper drawbackMapper;
    @Autowired
    private DrawbackLogMapper drawbackLogMapper;

    public List<Drawback> getDrawbacks(Integer jailId, String prisonerName, String phone, String name, int status, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("status", status);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return drawBackCustomMapper.getDrawbacks(params);
    }

    public int countDrawbacks(Integer jailId, String prisonerName, String phone, String name, int status, int indexPage, int pageSize) {
        Integer startIndex = (indexPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jailId", jailId);
        params.put("prisonerName", prisonerName);
        params.put("phone", phone);
        params.put("name", name);
        params.put("status", status);
        params.put("startIndex", startIndex);
        params.put("pageSize", pageSize);
        return drawBackCustomMapper.countDrawbacks(params);
    }

    public Drawback getDrawbackById(Integer dId) {
        if (dId == null || dId == 0){
            return null;
        }
        return drawbackMapper.selectByPrimaryKey(dId);
    }

    public int updateDrawback(Integer dId, Integer verifyType) {
        if (verifyType == null){
            return 0;
        }
        if (dId == null || dId == 0){
            return 0;
        }
        Drawback drawback = drawbackMapper.selectByPrimaryKey(dId);
        drawback.setStatus(verifyType.byteValue());
        drawback.setUpdatedAt(new Date());
        return drawbackMapper.updateByPrimaryKeySelective(drawback);
    }

    public int addDrawbackLog(Integer dId, Integer verifyType, String verifyMsg, Integer userId) {
        if (verifyType == null){
            return 0;
        }
        DrawbackLog drawbackLog = new DrawbackLog();
        drawbackLog.setDrawbackId(dId);
        drawbackLog.setVerifyType(verifyType.byteValue());
        drawbackLog.setVerifyMsg(verifyMsg);
        drawbackLog.setCreatedAt(new Date());
        drawbackLog.setVerifyUser(userId);
        return drawbackLogMapper.insertSelective(drawbackLog);

    }
}
