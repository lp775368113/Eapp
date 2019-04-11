package com.entity;

import java.util.List;

import com.quyu.Pq;

public class Model {
	private Boolean success;
	private  String message;
	private List<Pq> dayData;
	private List<Pq> monthData;
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the dayData
	 */
	public List<Pq> getDayData() {
		return dayData;
	}
	/**
	 * @param dayData the dayData to set
	 */
	public void setDayData(List<Pq> dayData) {
		this.dayData = dayData;
	}
	/**
	 * @return the monthData
	 */
	public List<Pq> getMonthData() {
		return monthData;
	}
	/**
	 * @param monthData the monthData to set
	 */
	public void setMonthData(List<Pq> monthData) {
		this.monthData = monthData;
	}
	
}
