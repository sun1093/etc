package com.tradevan.etc.jpa.model;

import java.util.ArrayList;
import java.util.List;

public class EtcPBSRoads {
	
	private int count;
	
	private String version;
	
	private List<EtcPBSRoad> result = new ArrayList<EtcPBSRoad>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<EtcPBSRoad> getResult() {
		return result;
	}

	public void setResult(List<EtcPBSRoad> result) {
		this.result = result;
	}
}
