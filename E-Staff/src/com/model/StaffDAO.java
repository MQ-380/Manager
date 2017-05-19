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
 * A data access object (DAO) providing persistence and search support for Staff
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.Staff
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class StaffDAO {
	private static final Logger log = LoggerFactory.getLogger(StaffDAO.class);
	// property constants
	public static final String STAID = "staid";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String RANK = "rank";
	public static final String DEID = "deid";
	public static final String TRAINING = "training";
	public static final String SKILL = "skill";
	public static final String STATE = "state";
	public static final String LEAVENUM = "leavenum";

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

	public void save(Staff transientInstance) {
		log.debug("saving Staff instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	 public void deleteStaff(String[] staffID) {
	        for (int i = 0; i < staffID.length; i++) {
	            Staff staff = (Staff) findByStaid(staffID[i]).get(0);
	            delete(staff);
	        }
	 }
	public void delete(Staff persistentInstance) {
		log.debug("deleting Staff instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staff findById(java.lang.Integer id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = (Staff) getCurrentSession().get("com.model.Staff",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Staff instance) {
		log.debug("finding Staff instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.model.Staff")
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
		log.debug("finding Staff instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Staff as model where model."
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

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}

	public List findByDeid(Object deid) {
		return findByProperty(DEID, deid);
	}

	public List findByTraining(Object training) {
		return findByProperty(TRAINING, training);
	}

	public List findBySkill(Object skill) {
		return findByProperty(SKILL, skill);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByLeavenum(Object leavenum) {
		return findByProperty(LEAVENUM, leavenum);
	}

	public List findAll() {
		log.debug("finding all Staff instances");
		try {
			String queryString = "from Staff";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = (Staff) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Staff instance) {
		log.debug("attaching dirty Staff instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staff instance) {
		log.debug("attaching clean Staff instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StaffDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StaffDAO) ctx.getBean("StaffDAO");
	}
}