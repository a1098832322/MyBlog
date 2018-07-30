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

    //当前登录用户
    private static User currentUser;

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
     * @param user
     */
    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
