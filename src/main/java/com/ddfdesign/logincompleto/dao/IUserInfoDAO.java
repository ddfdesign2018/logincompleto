package com.ddfdesign.logincompleto.dao;

import java.util.List;
import com.ddfdesign.logincompleto.entity.Article;
import com.ddfdesign.logincompleto.entity.UserInfo;
public interface IUserInfoDAO {
    UserInfo getActiveUser(String userName);
    List<Article> getAllUserArticles();
    boolean createUser(UserInfo userInfo);
}
