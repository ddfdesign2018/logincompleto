package com.ddfdesign.logincompleto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddfdesign.logincompleto.dao.IUserInfoDAO;
import com.ddfdesign.logincompleto.entity.Article;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userInfoDAO;
    @Override
    public List<Article> getAllUserArticles(){
        return userInfoDAO.getAllUserArticles();
    }
}
