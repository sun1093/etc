package com.tradevan.etc.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ETC_PBS_ROAD", schema = "PETCMGR")
@NamedQueries({
		@NamedQuery(name = "EtcPBSRoad.getDataBetweenDts", query = "SELECT s FROM EtcPBSRoad s WHERE s.modDttm >= :startDts AND s.modDttm <= :endDts order by  s.modDttm desc") })
public class EtcPBSRoad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 848196275990633935L;

	@Id
	@Column(name = "ID", length = 30)
	private String id;
	
	@Column(name = "UID", length = 30)
	private String uid;

	@Column(name = "REGION", length = 5)
	private String region;

	@Column(name = "SRC_DETAIL", length = 100)
	private String srcdetail;

	@Column(name = "AREA_NM", length = 100)
	private String areaNm;

	@Column(name = "DIRECTION", length = 5)
	private String direction;

	@Column(name = "HAPPEN_TIME", length = 20)
	private String happentime;

	@Column(name = "ROAD_TYPE", length = 5)
	private String roadtype;

	@Column(name = "ROAD", length = 100)
	private String road;

	@Column(name = "MOD_DTTM", length = 50)
	private String modDttm;

	@Column(name = "COMMENT", length = 4096)
	private String comment;

	@Column(name = "HAPPEN_DATE", length = 15)
	private String happendate;

	@Column(name = "Y1")
	private String y1;

	@Column(name = "X1")
	private String x1;

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUID() {
		return uid;
	}


	public void setUID(String uID) {
		uid = uID;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getSrcdetail() {
		return srcdetail;
	}


	public void setSrcdetail(String srcdetail) {
		this.srcdetail = srcdetail;
	}


	public String getAreaNm() {
		return areaNm;
	}


	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getHappentime() {
		return happentime;
	}


	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}


	public String getRoadtype() {
		return roadtype;
	}


	public void setRoadtype(String roadtype) {
		this.roadtype = roadtype;
	}


	public String getRoad() {
		return road;
	}


	public void setRoad(String road) {
		this.road = road;
	}


	public String getModDttm() {
		return modDttm;
	}


	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getHappendate() {
		return happendate;
	}


	public void setHappendate(String happendate) {
		this.happendate = happendate;
	}


	public String getY1() {
		return y1;
	}


	public void setY1(String y1) {
		this.y1 = y1;
	}


	public String getX1() {
		return x1;
	}


	public void setX1(String x1) {
		this.x1 = x1;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "EtcPBSRoad [UID=" + uid + ", region=" + region + ", srcdetail=" + srcdetail + ", areaNm=" + areaNm
				+ ", direction=" + direction + ", happentime=" + happentime + ", roadtype=" + roadtype + ", road="
				+ road + ", modDttm=" + modDttm + ", comment=" + comment + ", happendate=" + happendate + ", y1=" + y1
				+ ", x1=" + x1 + "]";
	}
}
