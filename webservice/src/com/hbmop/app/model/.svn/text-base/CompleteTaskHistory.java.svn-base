package com.hbmop.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//已办任务所在的流程的历史
@Entity
@Table(name="completeTaskHistory")
public class CompleteTaskHistory {
	private int id;
	private String executionId;
	private String construction_name;//新建的物业点或者室分站点的名称
	private String comment;//评语
	private String flowInitiator;//流程最开始的发起人
	private String name;//活动名称
	private String tasker;//任务执行人
	private String tasker_role;
	private String receiveTime;//任务接收时间
	private String completionTime;//完成时间
	private String construction_code;//新建的物业点或者室分站点的编号
	private String construction_netType;//网络类型
	private String city;//所属地市
	
	//@wu 在流程图上显示原有流程历史信息
	private int x;//节点的x坐标
	private int y;//节点的y坐标
	private int height;//节点的高度
	private int width;//节点的宽度
	//@Dong 在已办处显示撤销按钮
	private int revokeFlag;
	
	public int getRevokeFlag() {
		return revokeFlag;
	}
	public void setRevokeFlag(int revokeFlag) {
		this.revokeFlag = revokeFlag;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getConstruction_name() {
		return construction_name;
	}
	public void setConstruction_name(String construction_name) {
		this.construction_name = construction_name;
	}
	public String getTasker_role() {
		return tasker_role;
	}
	public void setTasker_role(String tasker_role) {
		this.tasker_role = tasker_role;
	}
	public CompleteTaskHistory(String activity_name){//暂时将taske_id赋给tasker
		this.name=activity_name;
	}
	public CompleteTaskHistory(){
	}
	public String getFlowInitiator() {
		return flowInitiator;
	}
	public void setFlowInitiator(String flowInitiator) {
		this.flowInitiator = flowInitiator;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTasker() {
		return tasker;
	}
	public void setTasker(String tasker) {
		this.tasker = tasker;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getConstruction_code() {
		return construction_code;
	}
	public void setConstruction_code(String construction_code) {
		this.construction_code = construction_code;
	}
	public String getConstruction_netType() {
		return construction_netType;
	}
	public void setConstruction_netType(String construction_netType) {
		this.construction_netType = construction_netType;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
