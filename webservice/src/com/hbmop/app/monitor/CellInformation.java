package com.hbmop.app.monitor;


/**
 * 小区信息包括共址室分小区和宏站室分小区
 *
 */
public class CellInformation {

	private String LAC;
	private String CI;
	private String angle;//偏转角
	private String longitude;
	private String latitude;
	private String cellMark;//小区标识
	private String chName;//小区中文名称
	private String BSCName;//bsc名称,RNCName
	private String ChamberOf;//是否是室分“是”代表是室分 “否”代表是宏站
	private String traffic;//流量
	private String telTraffic;//话务量
	private String networkType;//网络类型
	private String distance;//距离宏站下的字段
	private String shareOf;//是否是共址小区，“是”代表是共址，否则不是共址
	public String getAngle() {
		return angle;
	}
	public void setAngle(String angle) {
		this.angle = angle;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLAC() {
		return LAC;
	}
	public void setLAC(String lAC) {
		LAC = lAC;
	}
	public String getCI() {
		return CI;
	}
	public void setCI(String cI) {
		CI = cI;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getTelTraffic() {
		return telTraffic;
	}
	public void setTelTraffic(String telTraffic) {
		this.telTraffic = telTraffic;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	
	public String getBSCName() {
		return BSCName;
	}
	public void setBSCName(String bSCName) {
		BSCName = bSCName;
	}
	public String getChamberOf() {
		return ChamberOf;
	}
	public void setChamberOf(String chamberOf) {
		ChamberOf = chamberOf;
	}
	public String getCellMark() {
		return cellMark;
	}
	public void setCellMark(String cellMark) {
		this.cellMark = cellMark;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getShareOf() {
		return shareOf;
	}
	public void setShareOf(String shareOf) {
		this.shareOf = shareOf;
	}
	
}
