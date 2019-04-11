package com.quyu;

import java.util.List;

import com.util.Tool;

public class Dq {
	private String name_code;
	private String name;
	private String xiaoliang;
	private String shuliang;
	private List<Pq> pq;
	
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
		int js=0;
		for(Pq onepq:pq) {
			if(onepq==null||onepq.getXiaoliang()==null) {
				havaNull=true;
				sum+=1;
			}else if("****".equals(onepq.getXiaoliang())){
				return "****";
			}else{
				xl+=Double.parseDouble(onepq.getXiaoliang());
			}
			js+=1;
		}
		if(sum==js) {
			return null;
		}
		else if(havaNull) {
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
		Boolean havaNull=false;//默认没有空数据
		int sum=0;
		int js=0;
		for(Pq onepq:pq) {
			if(onepq==null||onepq.getShuliang()==null) {
				havaNull=true;
				sum+=1;
			}else if("****".equals(onepq.getShuliang())){
				return "****";
			}else{
				sl+=Double.parseDouble(onepq.getShuliang());
			}
			js+=1;
		}
		if(sum==js) {
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
	 * @return the pq
	 */
	public List<Pq> getPq() {
		return pq;
	}
	/**
	 * @param pq the pq to set
	 */
	public void setPq(List<Pq> pq) {
		this.pq = pq;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dq [name_code=" + name_code + ", name=" + name + ", xiaoliang=" + xiaoliang + ", shuliang=" + shuliang
				+ ", pq=" + pq + "]";
	}
	
}
