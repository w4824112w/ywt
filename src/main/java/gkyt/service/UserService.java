package gkyt.service;

import com.alibaba.druid.util.StringUtils;

import gkyt.dao.UserMapper;
import gkyt.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import gkyt.utils.BCrypt;

/**
 * Created by lyt38 on 2017/6/9.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(String userName, Integer jailId, Integer role){
        if (StringUtils.isEmpty(userName) || jailId == null){
            return 0;
        }
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw("123456", BCrypt.gensalt());
        User user = new User();
        user.setUsername(userName);
        user.setJailId(jailId);
        user.setSalt(salt);
        user.setRole(role);
        user.setHashedPassword(password);
        user.setCreatedAt(new Date());
        userMapper.insertSelective(user);
        return user.getId();
    }


    public User getUserById(Integer userId) {
        if (userId == 0){
            return null;
        }
        return userMapper.selectByPrimaryKey(userId);
    }
}
