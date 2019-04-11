package com.entity;

public class Dd_Dept {
	private String id;
	private boolean createDeptGroup;
	private String name;
	private String parentid;
	private boolean autoAddUser;
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
	 * @return the createDeptGroup
	 */
	public boolean isCreateDeptGroup() {
		return createDeptGroup;
	}
	/**
	 * @param createDeptGroup the createDeptGroup to set
	 */
	public void setCreateDeptGroup(boolean createDeptGroup) {
		this.createDeptGroup = createDeptGroup;
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
	 * @return the parentid
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * @return the autoAddUser
	 */
	public boolean isAutoAddUser() {
		return autoAddUser;
	}
	/**
	 * @param autoAddUser the autoAddUser to set
	 */
	public void setAutoAddUser(boolean autoAddUser) {
		this.autoAddUser = autoAddUser;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dept [id=" + id + ", createDeptGroup=" + createDeptGroup + ", name=" + name + ", parentid=" + parentid
				+ ", autoAddUser=" + autoAddUser + "]";
	}
	
}
