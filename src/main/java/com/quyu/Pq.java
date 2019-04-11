package com.quyu;

import com.util.Tool;

public class Pq {
	private String name_code;
	private String name;
	private String xiaoliang;
	private String shuliang;
	
	/**
	 * @return the name_code
	 */
	public String getName_code() {
		return name_code;
	}
	/**
	 * @param name_code the name_code to set
	 */
	public void setName_code(String name_code) {
		this.name_code = name_code;
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
	 * @return the xiaoliang
	 */
	public String getXiaoliang() {
		return Tool.getDoubleString(Double.parseDouble(xiaoliang));
	}
	/**
	 * @param xiaoliang the xiaoliang to set
	 */
	public void setXiaoliang(String xiaoliang) {
		this.xiaoliang = xiaoliang;
	}
	/**
	 * @return the shuliang
	 */
	public String getShuliang() {
		return shuliang;
	}
	/**
	 * @param shuliang the shuliang to set
	 */
	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pq [name_code=" + name_code + ", name=" + name + ", xiaoliang=" + xiaoliang + ", shuliang=" + shuliang
				+ "]";
	}
	
}
