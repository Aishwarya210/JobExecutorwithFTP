package org.kp.salesconnect.biz.metadataloader.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FtpConstants {
    @Value("${ftp.hostname}")
    private String hostname;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.remoteFile.path}")
    private String remoteFile;
    @Value("${ftp.localDirFile.path}")
    private String localDirFile;
	public String getHostname() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getRemoteFile() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getLocalDirFile() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}