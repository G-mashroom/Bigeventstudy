package org.spstudy.service;

import org.spstudy.pojo.User;

public interface UserService {
    // 根据用户名搜索用户
    User findByUserName(String username);
    // 注册
    void register(String username,String password);
    //更新
    void update(User user);
    // 更新头像
    void updateAvatar(String avatarUrl);
    // 更新密码
    void updatePwd(String newPwd);
}
