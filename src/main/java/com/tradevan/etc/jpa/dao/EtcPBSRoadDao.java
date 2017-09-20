package com.tradevan.etc.jpa.dao;

import java.util.List;

import com.tradevan.etc.jpa.model.EtcPBSRoad;
import com.tradevan.etc.jpa.model.EtcPBSRoads;

public interface EtcPBSRoadDao {

	public void insert(EtcPBSRoad obj);
	
	public void insert(EtcPBSRoads objs);

	public void update(EtcPBSRoad obj);

	public int delete(EtcPBSRoad obj);

	public EtcPBSRoad findById(String id);

	public List<EtcPBSRoad> getDataBetweenDts(String startDts, String endDts);
}
