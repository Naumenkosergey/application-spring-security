package by.naumenko.dao.common;

import by.naumenko.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T findOne(Long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public Long save(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from" + clazz.getSimpleName(), clazz)
                .getResultList();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
