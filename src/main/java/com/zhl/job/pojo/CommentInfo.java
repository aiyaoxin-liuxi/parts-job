package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表实体类
 * @author 刘熙
 *
 */
public class CommentInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 工作表id
	 */
	private String workId;
	/**
	 * 兼职用户id
	 */
	private String pid;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 逻辑删除标识
	 */
	private String isdel;
	
	
	// get and set
	/**
	 * 获取兼职用户id pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 获取id id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取工作表id workId
	 */
	public String getWorkId() {
		return workId;
	}

	/**
	 * 设置工作表id workId
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}

	/**
	 * 设置兼职用户id pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取评论内容 comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置评论内容 comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取类型 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置类型 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置状态 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取创建时间 createdate
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置创建时间 createdate
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取逻辑删除标识 isdel
	 */
	public String getIsdel() {
		return isdel;
	}
	/**
	 * 设置逻辑删除标识 isdel
	 */
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
}
