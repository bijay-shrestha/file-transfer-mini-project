package com.boa.filetransferminiproject.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Properties;

import static com.boa.filetransferminiproject.constant.FileTransferConstant.FileTransferMessageConstant.FILE_SUCCESSFULLY_TRANSFERRED;
import static com.boa.filetransferminiproject.constant.FileTransferConstant.SFTPMessageConstant.*;

@Service
public class SFTPService {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.username}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    public void transferFile(String localFilePath, String remoteFilePath) throws Exception {
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            // Establishing JSch session
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);

            Properties config = new Properties();
            config.put(STRICT_HOST_KEY_CHECKING, NO);
            session.setConfig(config);

            session.connect();
            System.out.println(CONNECTED_TO_SFTP + host);

            // Open SFTP channel
            channelSftp = (ChannelSftp) session.openChannel(SFTP);
            channelSftp.connect();
            System.out.println(SFTP_CHANNEL_OPEN);

            // Transfer file
            try (FileInputStream fis = new FileInputStream(localFilePath)) {
                channelSftp.put(fis, remoteFilePath);
            }

            System.out.println(FILE_SUCCESSFULLY_TRANSFERRED + remoteFilePath);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
