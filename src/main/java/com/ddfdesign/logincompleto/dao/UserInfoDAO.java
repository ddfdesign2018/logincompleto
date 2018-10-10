package com.ddfdesign.logincompleto.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        System.out.println("getActive Antes de la lista");
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName= :username and enabled= :enabled")
                .setParameter("username", userName).setParameter("enabled", enabled).getResultList();
        System.out.println("Lista:" + list);
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
}
