package by.naumenko.daoimpl;

import by.naumenko.dao.BaseDao;
import by.naumenko.entity.BaseEntity;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
    private final Class<T> clazz;
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    @Override
    public T findOne(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root).where(
                criteriaBuilder.equal(root.get("id"), id)
        );
        return entityManager.createQuery(criteria).getSingleResult();

    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();

    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(findOne(id));

    }
}
