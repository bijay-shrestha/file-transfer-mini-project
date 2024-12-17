package com.boa.filetransferminiproject;

public class FileTransferConstant {

    public interface FileTransferMessageConstant{
        String FILE_DOES_NOT_EXIST="File does not exist at given location: ";
        String FILE_SUCCESSFULLY_TRANSFERRED="File successfully transferred to: ";
        String FILE_TRANSFER_FAILED="File transfer failed: ";
    }

    public interface SFTPMessageConstant{
        String STRICT_HOST_KEY_CHECKING="StrictHostKeyChecking";
        String NO = "no";
        String CONNECTED_TO_SFTP="Connected to SFTP server: ";
        String SFTP_CHANNEL_OPEN= "SFTP channel opened.";
        String SFTP="sftp";
    }
}
