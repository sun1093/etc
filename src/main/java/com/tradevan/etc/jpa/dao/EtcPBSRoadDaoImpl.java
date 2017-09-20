package com.tradevan.etc.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tradevan.etc.jpa.model.EtcPBSRoad;
import com.tradevan.etc.jpa.model.EtcPBSRoads;

@Repository("EtcPBSRoadDaoImpl")
public class EtcPBSRoadDaoImpl implements EtcPBSRoadDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insert(EtcPBSRoad obj) {
		entityManager.persist(obj);
		entityManager.flush();
	}

	@Override
	public void update(EtcPBSRoad obj) {
		entityManager.merge(obj);
		entityManager.flush();
	}

	@Override
	public int delete(EtcPBSRoad obj) {
		Serializable findById = findById(obj.getId());

		if (findById != null) {
			entityManager.remove(findById);
			return 0;
		}
		return 1;
	}

	@Override
	public EtcPBSRoad findById(String id) {
		try {
			return entityManager.find(EtcPBSRoad.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<EtcPBSRoad> getDataBetweenDts(String startDts, String endDts) {
		TypedQuery<EtcPBSRoad> query = entityManager.createNamedQuery("EtcPBSRoad.getDataBetweenDts", EtcPBSRoad.class);
		query.setParameter("startDts", startDts);
		query.setParameter("endDts", endDts);
		return query.getResultList();
	}

	@Override
	public void insert(EtcPBSRoads objs) {
		for(EtcPBSRoad road: objs.getResult()) {
			road.setId(road.getUID());
			entityManager.persist(road);
		}
		entityManager.flush();
	}

}
