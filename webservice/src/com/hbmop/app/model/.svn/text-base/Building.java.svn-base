package com.hbmop.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hbmop.app.util.DataAdapter;

@Table(name="building")
@Entity
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(name="Building")
public class Building {
	private int id;
	private String building_code;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String name;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String ever_name;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String city;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String region;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String address;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String cover_type;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String cover_scene;
	@XmlElement(required=true,nillable=true)
	private double longitude;
	@XmlElement(required=true,nillable=true)
	private double latitude;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String atu_code;
	@XmlElement(required=true,nillable=true)
	private float square;
	@XmlElement(required=true,nillable=true)
	private float construction_square;
	@XmlElement(required=true,nillable=true)
	private int building_num;
	@XmlElement(required=true,nillable=true)
	private int floor_num;
	@XmlElement(required=true,nillable=true)
	private int elevator_num;
//	private String photo_path;
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String flow_id;
//	private String ProcessInitiator;//流程的发起者
	

	// 状态：0：所有可显示   1：可查询，不可更改   2：不可查询    0:完结站点   1: 新建流程中的对象      2:被物业点占用
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String state;

//	private Integer idCount;
//	private String photo_path0;
//	private String photo_path90;
//	private String photo_path180;
//	private String photo_path270;
//	private String photo_pandect;//照片总览
//	/**
//	 * @ author : DongYunfeng
//	 * @Date : 2014年10月21日
//	 */
//	private String floor_report;//扫楼报告（二期）
	//@wu 10.31
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String involved_property_code;//关联物业点编号
	@XmlJavaTypeAdapter(value = DataAdapter.class)
	@XmlElement(required=true,nillable=true)
	private String child_property_code;//关联子物业点编号
	
	
	
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
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
	
	
	

}
