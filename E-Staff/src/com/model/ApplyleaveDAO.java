package com.model;

import java.util.Date;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Applyleave entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Applyleave
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ApplyleaveDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ApplyleaveDAO.class);
	// property constants
	public static final String LEAVEID = "leaveid";
	public static final String STAID = "staid";
	public static final String TYPE = "type";
	public static final String DESCRIPTION = "description";
	public static final String ISCONFIRM = "isconfirm";
	public static final String OPERATOR = "operator";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Applyleave transientInstance) {
		log.debug("saving Applyleave instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Applyleave persistentInstance) {
		log.debug("deleting Applyleave instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Applyleave findById(java.lang.Integer id) {
		log.debug("getting Applyleave instance with id: " + id);
		try {
			Applyleave instance = (Applyleave) getCurrentSession().get(
					"com.model.Applyleave", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Applyleave instance) {
		log.debug("finding Applyleave instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.model.Applyleave")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Applyleave instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Applyleave as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLeaveid(Object leaveid) {
		return findByProperty(LEAVEID, leaveid);
	}

	public List findByStaid(Object staid) {
		return findByProperty(STAID, staid);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByIsconfirm(Object isconfirm) {
		return findByProperty(ISCONFIRM, isconfirm);
	}

	public List findByOperator(Object operator) {
		return findByProperty(OPERATOR, operator);
	}

	public List findAll() {
		log.debug("finding all Applyleave instances");
		try {
			String queryString = "from Applyleave";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Applyleave merge(Applyleave detachedInstance) {
		log.debug("merging Applyleave instance");
		try {
			Applyleave result = (Applyleave) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Applyleave instance) {
		log.debug("attaching dirty Applyleave instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Applyleave instance) {
		log.debug("attaching clean Applyleave instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ApplyleaveDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ApplyleaveDAO) ctx.getBean("ApplyleaveDAO");
	}
}