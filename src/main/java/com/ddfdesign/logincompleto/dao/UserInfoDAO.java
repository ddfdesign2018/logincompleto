package com.ddfdesign.logincompleto.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ddfdesign.logincompleto.entity.Article;
import com.ddfdesign.logincompleto.entity.UserInfo;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public UserInfo getActiveUser(String userName) {
        System.out.println("getActive user inicio");
        UserInfo activeUserInfo = new UserInfo();
        short enabled = 1;
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName= :username and enabled= :enabled")
                .setParameter("username", userName).setParameter("enabled", enabled).getResultList();

        if(!list.isEmpty()) {
            activeUserInfo = (UserInfo)list.get(0);
        }
        System.out.println("usuario activo: " + activeUserInfo);
        return activeUserInfo;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getAllUserArticles() {
        String hql = "FROM Article as atcl ORDER BY atcl.articleId";
        return (List<Article>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public boolean createUser(UserInfo userInfo){
        boolean check = false;
        String username = userInfo.getUserName();
        String password = userInfo.getPassword();

        String sql = "INSERT INTO users (username, password, full_name, role, country, enabled) VALUES (:username, :password, 'Test', 'ROLE_ADMIN', 'India', 1)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("username", username)
                .setParameter("password", password)
                .executeUpdate();
        return check;
    }
}
