package by.naumenko.dao;

import by.naumenko.dao.common.BaseDaoImpl;
import by.naumenko.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDoaImpl extends BaseDaoImpl<User> implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User>  itemRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("username"),name));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
