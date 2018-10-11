package com.ddfdesign.logincompleto.service;

import java.util.List;

import com.ddfdesign.logincompleto.entity.UserInfo;
import org.springframework.security.access.annotation.Secured;
import com.ddfdesign.logincompleto.entity.Article;

public interface IUserInfoService {
    @Secured ({"ROLE_ADMIN"})
    List<Article> getAllUserArticles();
    boolean createUser(UserInfo userInfo);
}
