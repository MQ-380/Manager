package com.model;

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
 * Fundamentalsalary entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.Fundamentalsalary
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FundamentalsalaryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FundamentalsalaryDAO.class);
	// property constants
	public static final String RANK = "rank";
	public static final String AMOUNT = "amount";

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

	public void save(Fundamentalsalary transientInstance) {
		log.debug("saving Fundamentalsalary instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Fundamentalsalary persistentInstance) {
		log.debug("deleting Fundamentalsalary instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fundamentalsalary findById(java.lang.Integer id) {
		log.debug("getting Fundamentalsalary instance with id: " + id);
		try {
			Fundamentalsalary instance = (Fundamentalsalary) getCurrentSession()
					.get("com.model.Fundamentalsalary", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Fundamentalsalary instance) {
		log.debug("finding Fundamentalsalary instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.model.Fundamentalsalary")
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
		log.debug("finding Fundamentalsalary instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Fundamentalsalary as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}

	public List findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List findAll() {
		log.debug("finding all Fundamentalsalary instances");
		try {
			String queryString = "from Fundamentalsalary";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Fundamentalsalary merge(Fundamentalsalary detachedInstance) {
		log.debug("merging Fundamentalsalary instance");
		try {
			Fundamentalsalary result = (Fundamentalsalary) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Fundamentalsalary instance) {
		log.debug("attaching dirty Fundamentalsalary instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fundamentalsalary instance) {
		log.debug("attaching clean Fundamentalsalary instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FundamentalsalaryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FundamentalsalaryDAO) ctx.getBean("FundamentalsalaryDAO");
	}
}