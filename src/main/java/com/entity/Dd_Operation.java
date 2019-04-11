package com.entity;

import java.util.Date;

public class Dd_Operation {
	private Integer id;
	private String userid;
	private Integer operation_type;
	private String parameter;
	private String before_status;
	private String after_status;
	private String success;
	private String error_msg;
	private Date operation_time;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the operation_type
	 */
	public Integer getOperation_type() {
		return operation_type;
	}
	/**
	 * @param operation_type the operation_type to set
	 */
	public void setOperation_type(Integer operation_type) {
		this.operation_type = operation_type;
	}
	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}
	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	/**
	 * @return the before_status
	 */
	public String getBefore_status() {
		return before_status;
	}
	/**
	 * @param before_status the before_status to set
	 */
	public void setBefore_status(String before_status) {
		this.before_status = before_status;
	}
	/**
	 * @return the after_status
	 */
	public String getAfter_status() {
		return after_status;
	}
	/**
	 * @param after_status the after_status to set
	 */
	public void setAfter_status(String after_status) {
		this.after_status = after_status;
	}
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * @return the error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}
	/**
	 * @param error_msg the error_msg to set
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	/**
	 * @return the operation_time
	 */
	public Date getOperation_time() {
		return operation_time;
	}
	/**
	 * @param operation_time the operation_time to set
	 */
	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dd_Operation [id=" + id + ", userid=" + userid + ", operation_type=" + operation_type + ", parameter="
				+ parameter + ", before_status=" + before_status + ", after_status=" + after_status + ", success="
				+ success + ", error_msg=" + error_msg + ", operation_time=" + operation_time + "]";
	}
	
	
	public static Dd_Operation getInstance(String userid, Integer operation_type, String parameter, String success, String error_msg) {
		Dd_Operation op=new Dd_Operation();
		op.setUserid(userid);
		op.setOperation_type(operation_type);
		op.setParameter(parameter);
		op.setSuccess(success);
		op.setError_msg(error_msg);;
		op.setOperation_time(new Date());
		return op;
	}
	
}
