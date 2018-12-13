package com.br.neogrid.conferencetrackmanagementneogrid.utils;

import java.io.File;

import org.springframework.util.FileCopyUtils;

import com.br.neogrid.conferencetrackmanagementneogrid.exception.FileException;

public class FileUtill {

	public static boolean moveFile(String pathFrom, String pathTo) throws FileException {
		boolean success = false;
		String msgErro;
		pathTo = pathTo.replace(".", System.currentTimeMillis()+".");

		File fromFile = new File(pathFrom);

		if (!fromFile.exists()) {
			msgErro = String.format("File in path %s not found.", pathFrom);
			throw new FileException(msgErro);
		} else {
			try {
				FileCopyUtils.copy(new File(pathFrom), new File(pathTo));
				fromFile.delete();
				success = true;
			} catch (Exception ex) {
				msgErro = String.format("Error copying file %s.", ex.getMessage());
				throw new FileException(msgErro);
			}
			return success;
		}
	}
	
	public static boolean fileExist(String pathFile) {
		File file = new File(pathFile);
		return file.exists();
	}

}
