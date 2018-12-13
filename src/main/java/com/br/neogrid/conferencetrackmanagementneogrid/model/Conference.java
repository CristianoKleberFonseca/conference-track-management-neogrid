package com.br.neogrid.conferencetrackmanagementneogrid.model;

import java.util.ArrayList;
import java.util.List;

public class Conference {

	private int totalTimeProposedConference;
	private int quantityTracks;
	private int totalTalks;
	private List<Talk> talksList = new ArrayList<Talk>();
	
	public static final String LIGHTNING = "lightning";
	public static final int LIGHTNING_TIME = 5;
	public static final String LUNCH_TITLE = " Lunch";
	public static final String NETWORKING_TITLE = " Networking Event"; 

	public int getTotalTimeProposedConference() {
		return totalTimeProposedConference;
	}

	public void setTotalTimeProposedConference(int totalTimeProposedConference) {
		this.totalTimeProposedConference = totalTimeProposedConference;
	}

	public int getQuantityTracks() {
		return quantityTracks;
	}

	public void setQuantityTracks(int quantityTracks) {
		this.quantityTracks = quantityTracks;
	}

	public int getTotalTalks() {
		return totalTalks;
	}

	public void setTotalTalks(int totalTalks) {
		this.totalTalks = totalTalks;
	}

	public List<Talk> getTalksList() {
		return talksList;
	}

	public void setTalksList(List<Talk> talksList) {
		this.talksList = talksList;
	}
}
