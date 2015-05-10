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


@Table(name="indoor_site")
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="IndoorSite")
public class IndoorSite {
	
	private static final int Class = 0;
	private static final int XmlAdapter = 0;
	private int id;
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String indoor_site_code;//室分站点编号
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String indoor_site_name;//名称
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String network_type;//网络类型
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String city;//地市
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String region;//区县
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String merge_type;//合路类型
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String cover_region;//覆盖区域
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String corperation;//代维公司
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String construction_company;//建设单位
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String end_time;//竣工时间
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String check_time;//验收时间
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String flow_id;//只存放新建的flow_id
	
	@XmlElement(required=true,nillable=true)
    private Integer antena_num=0;//天线
	@XmlElement(required=true,nillable=true)
    private Integer division_num=0;//功分器
	@XmlElement(required=true,nillable=true)
    private Integer merge_num=0;//合路器
	@XmlElement(required=true,nillable=true)
    private Integer coupler_num=0;//耦合器
	
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String involved_property_code;//关联物业点  （二期  ---- 覆盖场景、覆盖类别）
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String child_property_code;//关联子物业点编号 （二期 ---  经度、 纬度、 占地面积、 地址、 层数、 单双路）
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String isVIP;//是否VIP(二期)
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String project_time;//工期(二期)
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String distributed_type_LTE;//LTE分布类型(二期)
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String cell_type;//(二期)信源小区类型
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String site_name_OMC;//(二期)基站名称OMC
	@XmlElement(required=true,nillable=true)
	@XmlJavaTypeAdapter(value = DataAdapter.class)
    private String cell_name_OMC;//(二期)小区名称OMC
    @XmlElement(required=true,nillable=true)
    @XmlJavaTypeAdapter(value = DataAdapter.class)
    private String address;//地址(子物业点)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChild_property_code() {
		return child_property_code;
	}
	public void setChild_property_code(String child_property_code) {
		this.child_property_code = child_property_code;
	}
	public String getIsVIP() {
		return isVIP;
	}
	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}
	public String getProject_time() {
		return project_time;
	}
	public void setProject_time(String project_time) {
		this.project_time = project_time;
	}
	public String getDistributed_type_LTE() {
		return distributed_type_LTE;
	}
	public void setDistributed_type_LTE(String distributed_type_LTE) {
		this.distributed_type_LTE = distributed_type_LTE;
	}
	public String getCell_type() {
		return cell_type;
	}
	public void setCell_type(String cell_type) {
		this.cell_type = cell_type;
	}
	public String getSite_name_OMC() {
		return site_name_OMC;
	}
	public void setSite_name_OMC(String site_name_OMC) {
		this.site_name_OMC = site_name_OMC;
	}
	public String getCell_name_OMC() {
		return cell_name_OMC;
	}
	public void setCell_name_OMC(String cell_name_OMC) {
		this.cell_name_OMC = cell_name_OMC;
	}
	public Integer getAntena_num() {
		return antena_num;
	}
	public void setAntena_num(Integer antena_num) {
		this.antena_num = antena_num;
	}
	public Integer getDivision_num() {
		return division_num;
	}
	public void setDivision_num(Integer division_num) {
		this.division_num = division_num;
	}
	public Integer getMerge_num() {
		return merge_num;
	}
	public void setMerge_num(Integer merge_num) {
		this.merge_num = merge_num;
	}
	public Integer getCoupler_num() {
		return coupler_num;
	}
	public void setCoupler_num(Integer coupler_num) {
		this.coupler_num = coupler_num;
	}
	
	public String getIndoor_site_code() {
		return indoor_site_code;
	}
	public void setIndoor_site_code(String indoor_site_code) {
		this.indoor_site_code = indoor_site_code;
	}
	public String getIndoor_site_name() {
		return indoor_site_name;
	}
	public void setIndoor_site_name(String indoor_site_name) {
		this.indoor_site_name = indoor_site_name;
	}
	public String getNetwork_type() {
		return network_type;
	}
	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getInvolved_property_code() {
		return involved_property_code;
	}
	public void setInvolved_property_code(String involved_property_code) {
		this.involved_property_code = involved_property_code;
	}
	public String getMerge_type() {
		return merge_type;
	}
	public void setMerge_type(String merge_type) {
		this.merge_type = merge_type;
	}
	public String getCover_region() {
		return cover_region;
	}
	public void setCover_region(String cover_region) {
		this.cover_region = cover_region;
	}
	public String getCorperation() {
		return corperation;
	}
	public void setCorperation(String corperation) {
		this.corperation = corperation;
	}
	public String getConstruction_company() {
		return construction_company;
	}
	public void setConstruction_company(String construction_company) {
		this.construction_company = construction_company;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
}
