package com.tradevan.etc.jpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradevan.etc.jpa.dao.EtcPBSRoadDao;
import com.tradevan.etc.jpa.model.EtcPBSRoad;
import com.tradevan.etc.jpa.model.EtcPBSRoads;

@Service("EtcPBSRoadService")
@Transactional(readOnly = true)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EtcPBSRoadService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EtcPBSRoadDao etcPBSRoadDao;

	@Transactional(readOnly = false)
	public void insert(EtcPBSRoad obj) {
		etcPBSRoadDao.insert(obj);
		logger.debug("UId of new iotConnLog " + obj.getUID());
	}

	public void insert(EtcPBSRoads objs) {
		etcPBSRoadDao.insert(objs);
	}

	
	@Transactional(readOnly = false)
	public void delete(EtcPBSRoad roadData) {
		etcPBSRoadDao.delete(roadData);
	}

	@Transactional(readOnly = false)
	public void update(EtcPBSRoad log) {
		etcPBSRoadDao.update(log);
	}
	
	public EtcPBSRoad findById(String id) {
		return etcPBSRoadDao.findById(id);
	}
	
	public List<EtcPBSRoad> getDataBetweenDts(String startDts, String endDts) {
		return etcPBSRoadDao.getDataBetweenDts(startDts, endDts);
	}

}
