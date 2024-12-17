package com.boa.filetransferminiproject.controller;

import com.boa.filetransferminiproject.utils.SFTPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

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
            return ResponseEntity.badRequest().body("File does not exist at given location: " + filePath);
        }

        try {
            sftpService.transferFile(filePath, remotePath);
            return ResponseEntity.ok("File successfully transferred to: " + remotePath);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File transfer failed: " + e.getMessage());
        }
    }

}
