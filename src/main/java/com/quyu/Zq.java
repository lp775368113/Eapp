package com.quyu;

import java.util.List;

import com.util.Tool;

public class Zq {
	private String name_code;
	private String name;
	private String xiaoliang;
	private String shuliang;
	private List<Dq> dq;
	
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
		Double xl=(double) 0;
		Boolean havaNull=false;//默认没有空数据
		int sum=0;
		for(Dq onedq:dq) {
			if(onedq==null||onedq.getXiaoliang()==null) {
				havaNull=true;
				sum+=1;
			}else if("****".equals(onedq.getXiaoliang())){
				return "****";
			}else{
				xl+=Double.parseDouble(onedq.getXiaoliang());
			}
		}
		if(sum==dq.size()) {
			return null;
		}else if(havaNull) {
			return "****";
		}
		return Tool.getDoubleString(xl);
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
		Double sl=(double) 0;
		int sum=0;
		Boolean havaNull=false;//默认没有空数据
		for(Dq onedq:dq) {
			if(onedq==null||onedq.getShuliang()==null) {
				havaNull=true;
				sum+=1;
			}else if("****".equals(onedq.getShuliang())){
				return "****";
			}else{
				sl+=Double.parseDouble(onedq.getShuliang());
			}
		}
		if(sum==dq.size()) {
			return null;
		}else if(havaNull) {
			return "****";
		}
		return Tool.getDoubleString(sl);
	}
	/**
	 * @param shuliang the shuliang to set
	 */
	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}
	/**
	 * @return the dq
	 */
	public List<Dq> getDq() {
		return dq;
	}
	/**
	 * @param dq the dq to set
	 */
	public void setDq(List<Dq> dq) {
		this.dq = dq;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Zq [name_code=" + name_code + ", name=" + name + ", xiaoliang=" + xiaoliang + ", shuliang=" + shuliang
				+ ", dq=" + dq + "]";
	}
		
}
