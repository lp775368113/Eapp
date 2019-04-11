package com.entity;

public class Dd_Info_Dic {
	private String id;
	private String code_type;
	private String name;
	private String code;
	private String priority;
	private String remark;
	private String removed;
	private Boolean checked;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the code_type
	 */
	public String getCode_type() {
		return code_type;
	}
	/**
	 * @param code_type the code_type to set
	 */
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the removed
	 */
	public String getRemoved() {
		return removed;
	}
	/**
	 * @param removed the removed to set
	 */
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dd_Info_Dic [id=" + id + ", code_type=" + code_type + ", name=" + name + ", code=" + code
				+ ", priority=" + priority + ", remark=" + remark + ", removed=" + removed + ", checked=" + checked
				+ "]";
	}
	
}
