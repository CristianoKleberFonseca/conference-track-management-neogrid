package com.br.neogrid.conferencetrackmanagementneogrid.model;

public class Talk {

	private int id;
	private int minutes;
	private String sessionTime;
	private String title;
	private String networkingTitle;
	private String lunchTitle;
	private String trackTitle;
	private boolean lunchFlag = false;
	private boolean networkingFlag = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetworkingTitle() {
		return networkingTitle;
	}

	public void setNetworkingTitle(String networkingTitle) {
		this.networkingTitle = networkingTitle;
	}

	public String getLunchTitle() {
		return lunchTitle;
	}

	public void setLunchTitle(String lunchTitle) {
		this.lunchTitle = lunchTitle;
	}

	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public boolean isLunchFlag() {
		return lunchFlag;
	}

	public void setLunchFlag(boolean lunchFlag) {
		this.lunchFlag = lunchFlag;
	}

	public boolean isNetworkingFlag() {
		return networkingFlag;
	}

	public void setNetworkingFlag(boolean networkingFlag) {
		this.networkingFlag = networkingFlag;
	}
}
