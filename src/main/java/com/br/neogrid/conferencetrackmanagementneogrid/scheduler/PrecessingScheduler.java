package com.br.neogrid.conferencetrackmanagementneogrid.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.neogrid.conferencetrackmanagementneogrid.service.ConferenceService;
import com.br.neogrid.conferencetrackmanagementneogrid.utils.FileUtill;

@Component
public class PrecessingScheduler {
	
	private static final Logger LOGGER = LogManager.getLogger(PrecessingScheduler.class);	
		
	@Value("${conference.minutes.first.turn}")
	private int minutesFirstTurn;
	@Value("${conference.minutes.second.turn}")
	private int minutesSecondTurn;
	@Value("${conference.total.conference.time}")
	private Double totalTimeConference;
	@Value("${conference.name.input.file}")	
	private String filePath;
	@Value("${conference.destiny.path}")	
	private String destinyPath;
	
    @Scheduled(cron = "0 */1 * * * *")
    public void processingVotes() {
    	ConferenceService conferenceService = new ConferenceService();
    	try {
    		PrecessingScheduler.LOGGER.info("Start of conference track processing.");
			if(FileUtill.fileExist(filePath)) {
				conferenceService.createConference(filePath, totalTimeConference, minutesFirstTurn, minutesSecondTurn);
				FileUtill.moveFile(this.filePath, this.destinyPath);
			} else {
				PrecessingScheduler.LOGGER.info(String.format("File in path %s not found.", filePath));
			}
		} catch (Exception e) {
			PrecessingScheduler.LOGGER.error("Erro with conference track processing: "+e.getMessage());			
		} finally {
			PrecessingScheduler.LOGGER.info("End of conference track processing.");
		}
    	
    }

}
