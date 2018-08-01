package org.sang.service;

import org.sang.dao.UserDao;
import org.sang.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sang on 17-3-10.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 错误代码们
     */
    public static final int SUCCESS = 1;
    public static final int FAIL_WITH_UNKNOW_REASON = -1;
    public static final int DUPLICATE_NAME = 0;


    //当前登录用户
    private static User currentUser;

    public int register(String username, String password, String nickname) {
        User user = userDao.queryByNickName(nickname);
        if (user != null) {
            //如果结果集不为空则证明昵称已被使用
            return DUPLICATE_NAME;
        } else {
            //否则注册成功
            user = new User(username, password, nickname);
            try {
                userDao.register(user);
                if (user.getId() != 0L) {
                    return SUCCESS;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return FAIL_WITH_UNKNOW_REASON;
        }

    }

    public boolean login(String username, String password) {
        User user = userDao.getUser(username, password);
        if (user == null) {
            return false;
        } else {
            currentUser = user;
            return true;
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * setter
     *
     * @param user
     */
    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
