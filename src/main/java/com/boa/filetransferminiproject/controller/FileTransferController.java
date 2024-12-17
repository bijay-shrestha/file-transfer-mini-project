package com.boa.filetransferminiproject.controller;

import com.boa.filetransferminiproject.utils.SFTPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import static com.boa.filetransferminiproject.constant.FileTransferConstant.FileTransferMessageConstant.*;

@RestController
@RequestMapping("/api/transfer")
public class FileTransferController {

    private final SFTPService sftpService;

    public FileTransferController(SFTPService sftpService) {
        this.sftpService = sftpService;
    }


    @PostMapping
    public ResponseEntity<String> transferFile(@RequestParam String filePath,
                                               @RequestParam String remotePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.badRequest().body(FILE_DOES_NOT_EXIST + filePath);
        }

        try {
            sftpService.transferFile(filePath, remotePath);
            return ResponseEntity.ok(FILE_SUCCESSFULLY_TRANSFERRED + remotePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FILE_TRANSFER_FAILED + e.getMessage());
        }
    }

}
