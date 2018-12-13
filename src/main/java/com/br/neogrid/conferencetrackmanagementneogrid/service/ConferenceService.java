package com.br.neogrid.conferencetrackmanagementneogrid.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.br.neogrid.conferencetrackmanagementneogrid.model.Conference;
import com.br.neogrid.conferencetrackmanagementneogrid.model.Talk;
import com.br.neogrid.conferencetrackmanagementneogrid.model.TalksComparator;

@Service
public class ConferenceService {
	private static final Logger LOGGER = LogManager.getLogger(ConferenceService.class);

	private int talkIndex;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
	private Calendar calendar = new GregorianCalendar();

	public void createConference(String filePath, Double totalTimeConference, int minutesFirstTurn,
			int minutesSecondTurn) {
		// Método que irá criar o objeto que representa a conferência.
		int startTalkIndex = 0;
		Conference conference = this.buildConference(filePath, totalTimeConference);

		for (int trackCount = 0; trackCount < conference.getQuantityTracks(); trackCount++) {
			this.scheduling(conference, trackCount, startTalkIndex, minutesFirstTurn, minutesSecondTurn);
		}
		this.outputOfTalksIntoTracks(conference.getTalksList());
	}

	private Conference buildConference(String filePath, Double totalTimeConference) {
		Conference conference = new Conference();
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		String fileLine = null;

		try {
			fileInputStream = new FileInputStream(filePath);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
			int count = 0;

			while ((fileLine = bufferedReader.readLine()) != null) {
				if (fileLine.length() < 2 || fileLine.isEmpty()) {
					continue;
				}
				Talk talk = new Talk();

				talk.setId(++count);
				talk.setTitle(fileLine.substring(0, fileLine.lastIndexOf(" ")));

				if (Conference.LIGHTNING.equals(fileLine.substring(fileLine.lastIndexOf(" ") + 1))) {
					// Setar o tempo das palestras relâmpago.
					talk.setMinutes(Conference.LIGHTNING_TIME);
				} else {
					// Recuperar o tempo de cada palestra utilizando expressão regular.
					talk.setMinutes(Integer.parseInt(fileLine.replaceAll("\\D+", "")));
				}
				conference.setTotalTimeProposedConference(
						conference.getTotalTimeProposedConference() + talk.getMinutes());
				conference.getTalksList().add(talk);
			}
			conference.setTotalTalks(count);
			conference.setQuantityTracks(this.calculateQuantityTracks(
					Double.valueOf(conference.getTotalTimeProposedConference()), totalTimeConference));
			// Ordenação decrescente do array baseado no tempo de cada palestra.
			Collections.sort(conference.getTalksList(), new TalksComparator());

		} catch (FileNotFoundException ex) {
			ConferenceService.LOGGER.error(ex.getMessage());
		} catch (IOException ex) {
			ConferenceService.LOGGER.error(ex.getMessage());
		} catch (Exception ex) {
			ConferenceService.LOGGER.error(ex.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
				ConferenceService.LOGGER.error(ex.getMessage());
			}
		}

		return conference;
	}

	private int calculateQuantityTracks(Double totalTimeProposedConference, Double totalTimeConference) {
		int quantityTracks = 0;
		Double numberOfTracks = totalTimeProposedConference / totalTimeConference;
		double fractionalPart = numberOfTracks % 1;
		double integralPart = numberOfTracks - fractionalPart;

		int leftMinutes = totalTimeProposedConference.intValue() - (int) integralPart * totalTimeConference.intValue();

		if (leftMinutes > 0) {
			quantityTracks = (int) integralPart + 1;

		} else {
			quantityTracks = (int) integralPart;
		}

		return quantityTracks;
	}

	private void scheduling(Conference conference, int trackCountIndex, int startTalkIndex, int minutesFirstTurn,
			int minutesSecondTurn) {

		Talk talk = conference.getTalksList().get(this.talkIndex);

		// Baseando o calendário na hora de início das palestras.
		this.calendar.set(Calendar.HOUR, 9);
		this.calendar.set(Calendar.MINUTE, 0);
		this.calendar.set(Calendar.AM_PM, Calendar.AM);
		// Chamada do método sobrecarregado de agendamento das palestras.
		this.scheduling(conference, trackCountIndex, minutesFirstTurn);
		// Bloco de código que cria o horário do almoço.
		talk = conference.getTalksList().get(talkIndex);
		talk.setLunchFlag(true);
		talk.setLunchTitle(this.simpleDateFormat.format(this.calendar.getTime()) + Conference.LUNCH_TITLE);
		this.calendar.add(Calendar.MINUTE, 60);

		this.talkIndex++;
		// Chamada do método sobrecarregado de agendamento das palestras.
		this.scheduling(conference, trackCountIndex, minutesSecondTurn);

		// Prevenção para não ocorrer estourar de índice.
		if (conference.getTotalTalks() == (this.talkIndex)) {
			--this.talkIndex;
		}
		// Bloco de código que cria o horário de network.
		talk = conference.getTalksList().get(this.talkIndex);
		talk.setNetworkingFlag(true);
		talk.setNetworkingTitle(this.simpleDateFormat.format(this.calendar.getTime()) + Conference.NETWORKING_TITLE);

		this.talkIndex++;
	}

	private void outputOfTalksIntoTracks(List<Talk> talksList) {

		System.out.println("Test Output :");
		System.out.println("");
		String TrackTitle = "dummyValue";

		for (int trackCountIndex = 0; trackCountIndex < talksList.size(); trackCountIndex++) {

			if (!TrackTitle.equals(talksList.get(trackCountIndex).getTrackTitle())) {
				System.out.println(talksList.get(trackCountIndex).getTrackTitle() + ":");
				System.out.println("");
				TrackTitle = talksList.get(trackCountIndex).getTrackTitle();
			}

			System.out.println(talksList.get(trackCountIndex).getTitle());

			if (talksList.get(trackCountIndex).isLunchFlag()) {
				System.out.println(talksList.get(trackCountIndex).getLunchTitle());
			}

			if (talksList.get(trackCountIndex).isNetworkingFlag()) {
				System.out.println(talksList.get(trackCountIndex).getNetworkingTitle());
				System.out.println("");
				System.out.println("");
			}

		}
	}

	private void scheduling(Conference conference, int trackCountIndex, int minutesTurn) {

		Talk talk = conference.getTalksList().get(talkIndex);

		minutesTurn = minutesTurn - talk.getMinutes();
		talk.setTitle(
				simpleDateFormat.format(calendar.getTime()) + " " + talk.getTitle() + " " + talk.getMinutes() + "min");
		calendar.add(Calendar.MINUTE, talk.getMinutes());
		talk.setTrackTitle("Track" + " " + (trackCountIndex + 1));

		if (minutesTurn > 0 && (this.talkIndex < (conference.getTotalTalks() - 1))
				&& minutesTurn >= talk.getMinutes()) {
			++this.talkIndex;
			this.scheduling(conference, trackCountIndex, minutesTurn);
		}
	}
}
