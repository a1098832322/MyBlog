package org.sang.dao;

import org.apache.ibatis.annotations.Param;
import org.sang.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by sang on 17-3-10.
 */
@Repository
public interface UserDao {
    User getUser(@Param("username") String username, @Param("password") String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 根据昵称查找用户
     *
     * @param nickname
     * @return
     */
    User queryByNickName(@Param("nickname") String nickname);
}
