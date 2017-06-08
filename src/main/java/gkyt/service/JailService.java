package gkyt.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import gkyt.dao.JailMapper;
import gkyt.model.Jail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by lyt38 on 2017/6/8.
 */
@Service
public class JailService {
    @Autowired
    private JailMapper jailMapper;

    public int addJail(String prisonName){
        if (StringUtils.isEmpty(prisonName)){
            return 0;
        }
        Jail jail = new Jail();
        jail.setTitle(prisonName);
        jail.setCreatedAt(new Date());
        jail.setUpdatedAt(new Date());
        jailMapper.insertSelective(jail);
        return jail.getId();
    }
}
