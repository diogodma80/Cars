package ca.com.diogo.domain;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class HibernateDao<T> {

	protected Class<T> clazz;
	protected Session session;

	@Autowired
	private SessionFactory sessionFactory;

	public HibernateDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public T load(Serializable id) {
		return (T) getSession().load(this.clazz, id);
	}

	public T get(Serializable id) {
		return (T) getSession().get(this.clazz, id);
	}

	protected Query createQuery(String query) {
		return getSession().createQuery(query);
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(this.clazz);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		if (sessionFactory != null) {
			// session = sessionFactory.getCurrentSession();
			try {
				session = sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
				//If the getCurrentSession() method fails, the openSession() should do the trick.
				session = sessionFactory.openSession();
			}
		}
		if (session == null) {
			throw new RuntimeException("Hibernate session is null");
		}
		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}