package com.hbmop.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hbmop.app.util.DataAdapter;

@Table(name="child_property")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="ChildProperty")
public class ChildProperty {
	private int id;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String child_property_code;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String name;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String ever_name;			//曾用名
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String city;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String region;				//地区
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String address;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String cover_type;			//覆盖类别
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String cover_scene;			//覆盖场景
	private double longitude;			//经度
	private double latitude;			//纬度
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String atu_code;			//所属网格
	private float square;				//占地面积
	private float construction_square;	//建筑面积
	private int building_num;			//楼宇数量
	private int floor_num;				//楼层数量
	private int elevator_num;			//电梯数量
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String flow_id;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String state;				//  0:完结站点      1: 新建流程中的对象   2:删除流程的对象   
	
	private Integer idCount;
	//@wu 10.31
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String involved_property_code;//关联物业点编号
	//是否关联楼宇
	/**
	 * 是否关联楼宇
	 * @author sunxingyang
	 * @Date 2014年12月11日13:26:59
	 */
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String buildingState;  //0：未关联  1：已关联
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String processInitiator;//流程的发起者
	
	
	public String getBuildingState() {
		return buildingState;
	}
	public void setBuildingState(String buildingState) {
		this.buildingState = buildingState;
	}
	public Integer getIdCount() {
		return idCount;
	}
	public void setIdCount(Integer idCount) {
		this.idCount = idCount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEver_name() {
		return ever_name;
	}
	public void setEver_name(String ever_name) {
		this.ever_name = ever_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCover_type() {
		return cover_type;
	}
	public void setCover_type(String cover_type) {
		this.cover_type = cover_type;
	}
	public String getCover_scene() {
		return cover_scene;
	}
	public void setCover_scene(String cover_scene) {
		this.cover_scene = cover_scene;
	}
	
	public String getAtu_code() {
		return atu_code;
	}
	public void setAtu_code(String atu_code) {
		this.atu_code = atu_code;
	}
	public float getSquare() {
		return square;
	}
	public void setSquare(float square) {
		this.square = square;
	}
	public float getConstruction_square() {
		return construction_square;
	}
	public void setConstruction_square(float construction_square) {
		this.construction_square = construction_square;
	}
	public int getBuilding_num() {
		return building_num;
	}
	public void setBuilding_num(int building_num) {
		this.building_num = building_num;
	}
	public int getFloor_num() {
		return floor_num;
	}
	public void setFloor_num(int floor_num) {
		this.floor_num = floor_num;
	}
	public int getElevator_num() {
		return elevator_num;
	}
	public void setElevator_num(int elevator_num) {
		this.elevator_num = elevator_num;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvolved_property_code() {
		return involved_property_code;
	}
	public void setInvolved_property_code(String involved_property_code) {
		this.involved_property_code = involved_property_code;
	}
	public String getChild_property_code() {
		return child_property_code;
	}
	public void setChild_property_code(String child_property_code) {
		this.child_property_code = child_property_code;
	}
	public String getProcessInitiator() {
		return processInitiator;
	}
	public void setProcessInitiator(String processInitiator) {
		this.processInitiator = processInitiator;
	}
	
	
	
	

}
