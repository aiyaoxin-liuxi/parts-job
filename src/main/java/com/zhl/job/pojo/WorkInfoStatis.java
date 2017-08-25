package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位发布统计表实体
 * @author 刘熙
 *
 */
public class WorkInfoStatis implements Serializable {

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
	 * 阅读个数
	 */
	private int loadNum;
	/**
	 * 申请个数
	 */
	private int applyNum;
	/**
	 * 录取个数
	 */
	private int employNum;
	/**
	 * 评论个数
	 */
	private int commentNum;
	/**
	 * 关注个数
	 */
	private int attentionNum;
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
	 * 获取阅读个数 loadNum
	 */
	public int getLoadNum() {
		return loadNum;
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
	 * 设置阅读个数 loadNum
	 */
	public void setLoadNum(int loadNum) {
		this.loadNum = loadNum;
	}
	/**
	 * 获取申请个数 applyNum
	 */
	public int getApplyNum() {
		return applyNum;
	}
	/**
	 * 设置申请个数 applyNum
	 */
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	/**
	 * 获取评论个数 commentNum
	 */
	public int getCommentNum() {
		return commentNum;
	}
	/**
	 * 设置评论个数 commentNum
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	/**
	 * 获取关注个数 attentionNum
	 */
	public int getAttentionNum() {
		return attentionNum;
	}
	/**
	 * 设置关注个数 attentionNum
	 */
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
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
	 * 获取录取个数 employNum
	 */
	public int getEmployNum() {
		return employNum;
	}
	/**
	 * 设置录取个数 employNum
	 */
	public void setEmployNum(int employNum) {
		this.employNum = employNum;
	}
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorkInfoStatis [id=" + id + ", workId=" + workId + ", loadNum=" + loadNum + ", applyNum=" + applyNum
                + ", employNum=" + employNum + ", commentNum=" + commentNum + ", attentionNum=" + attentionNum
                + ", type=" + type + ", state=" + state + ", createdate=" + createdate + ", isdel=" + isdel + "]";
    }
	
}
