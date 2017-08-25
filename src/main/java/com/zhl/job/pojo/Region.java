package com.zhl.job.pojo;

/**
 * 省市区字典表
 * @author 刘熙
 */
public class Region {

	/**
	 * id
	 */
	private String regionId;
	/**
	 * 父id
	 */
	private String parentId;
	/**
	 * 名称
	 */
	private String regionName;
	/**
	 * 类型
	 * 1省  2市  3区
	 */
	private String regionType;
	/**
	 * 状态 00显示；01隐藏
	 */
	private String regionState;
	/**
	 * 获取id regionId
	 */
	public String getRegionId() {
		return regionId;
	}
	/**
	 * 设置id regionId
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	/**
	 * 获取父id parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置父id parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取名称 regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 设置名称 regionName
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 获取类型1省2市3区 regionType
	 */
	public String getRegionType() {
		return regionType;
	}
	/**
	 * 设置类型1省2市3区 regionType
	 */
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	/**
	 * 获取状态00显示；01隐藏 regionState
	 */
	public String getRegionState() {
		return regionState;
	}
	/**
	 * 设置状态00显示；01隐藏 regionState
	 */
	public void setRegionState(String regionState) {
		this.regionState = regionState;
	}
	
}
