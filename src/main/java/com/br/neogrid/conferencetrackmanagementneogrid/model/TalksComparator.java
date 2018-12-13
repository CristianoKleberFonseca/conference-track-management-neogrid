package com.br.neogrid.conferencetrackmanagementneogrid.model;

import java.util.Comparator;

public class TalksComparator implements Comparator<Talk> {

	@Override
	public int compare(Talk o1, Talk o2) {
		if (o1.getMinutes() < o2.getMinutes()) {
			return 1;
		} else {
			return -1;
		}
	}

}
