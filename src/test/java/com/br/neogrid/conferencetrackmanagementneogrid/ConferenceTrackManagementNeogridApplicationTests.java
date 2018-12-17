package com.br.neogrid.conferencetrackmanagementneogrid;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.neogrid.conferencetrackmanagementneogrid.exception.ConferenceException;
import com.br.neogrid.conferencetrackmanagementneogrid.service.ConferenceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceTrackManagementNeogridApplicationTests {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void sceneryTestEmptyFile() throws ConferenceException {
		this.expectedException.expect(ConferenceException.class);
		this.expectedException.expectMessage("File is empty.");
		ConferenceService conferenceService = new ConferenceService();
		
		conferenceService.createConference("src//test//java//com//br//neogrid//conferencetrackmanagementneogrid/file//input_file_empty.txt", 420.0D, 180, 240);
	}
	
	@Test
	public void sceneryTestFileNotExistes() throws ConferenceException {
		this.expectedException.expect(ConferenceException.class);
		this.expectedException.expectMessage("File not exists.");
		ConferenceService conferenceService = new ConferenceService();
		
		conferenceService.createConference("src//test//java//com//br//neogrid//conferencetrackmanagementneogrid/file//xpto.txt", 420.0D, 180, 240);
	}
	
	@Test
	public void sceneryHappyWay()  throws ConferenceException {
		ConferenceService conferenceService = new ConferenceService();
		boolean returnConference = conferenceService.createConference("src//test//java//com//br//neogrid//conferencetrackmanagementneogrid/file//input.txt", 420.0D, 180, 240);
		assertTrue(returnConference);
	}
}

