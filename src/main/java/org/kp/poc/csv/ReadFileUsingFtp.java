package org.kp.poc.csv;

import java.io.IOException;

import org.kp.salesconnect.biz.metadataloader.constant.FtpConstants;
import org.springframework.beans.factory.annotation.Autowired;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;


public class ReadFileUsingFtp {

	@Autowired
	private FtpConstants ftpConstants;
	
	private SSHClient setupSshj() throws IOException {
		System.out.println("Setting up SSH Client");
		SSHClient client = new SSHClient();
		client.addHostKeyVerifier(new PromiscuousVerifier());
		client.connect(ftpConstants.getHostname());
		client.authPassword(ftpConstants.getUsername(), ftpConstants.getPassword());
		return client;
	}
		 
	
	private void downloadFile() throws IOException {
		System.out.println("Downloading File from FTP server");
		SSHClient sshClient = setupSshj();
		SFTPClient sftpClient = sshClient.newSFTPClient();
		sftpClient.get(ftpConstants.getRemoteFile(), ftpConstants.getLocalDirFile());
		sftpClient.close();
		sshClient.disconnect();
	}
		 
	
	
}
