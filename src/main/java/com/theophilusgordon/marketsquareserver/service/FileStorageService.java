package com.theophilusgordon.marketsquareserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface FileStorageService {
    public String uploadFile(MultipartFile file);
}
