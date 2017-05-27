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
 * Rewardandpunish entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.Rewardandpunish
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RewardandpunishDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RewardandpunishDAO.class);
	// property constants
	public static final String STAID = "staid";
	public static final String TYPE = "type";
	public static final String AMOUNT = "amount";
	public static final String DESCRIPTION = "description";
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

	public void save(Rewardandpunish transientInstance) {
		log.debug("saving Rewardandpunish instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Rewardandpunish persistentInstance) {
		log.debug("deleting Rewardandpunish instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rewardandpunish findById(java.lang.Integer id) {
		log.debug("getting Rewardandpunish instance with id: " + id);
		try {
			Rewardandpunish instance = (Rewardandpunish) getCurrentSession()
					.get("com.model.Rewardandpunish", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Rewardandpunish instance) {
		log.debug("finding Rewardandpunish instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.model.Rewardandpunish")
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
		log.debug("finding Rewardandpunish instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Rewardandpunish as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStaid(Object staid) {
		return findByProperty(STAID, staid);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByOperator(Object operator) {
		return findByProperty(OPERATOR, operator);
	}

	public List findAll() {
		log.debug("finding all Rewardandpunish instances");
		try {
			String queryString = "from Rewardandpunish";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Rewardandpunish merge(Rewardandpunish detachedInstance) {
		log.debug("merging Rewardandpunish instance");
		try {
			Rewardandpunish result = (Rewardandpunish) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Rewardandpunish instance) {
		log.debug("attaching dirty Rewardandpunish instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rewardandpunish instance) {
		log.debug("attaching clean Rewardandpunish instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RewardandpunishDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RewardandpunishDAO) ctx.getBean("RewardandpunishDAO");
	}
}