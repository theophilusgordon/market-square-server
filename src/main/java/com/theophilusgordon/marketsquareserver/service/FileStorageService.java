package com.theophilusgordon.marketsquareserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String uploadFile(MultipartFile file);
}
