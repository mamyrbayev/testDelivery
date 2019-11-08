package com.delivery.testDelivery.file.services;

import com.delivery.testDelivery.file.exceptions.MyFileNotFoundException;
import com.delivery.testDelivery.file.models.entities.FileResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {


    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public FileResource save(FileResource fileResource);

    public boolean delete(FileResource fileResource);

    public FileResource getFileByFileName(String fileName) throws MyFileNotFoundException;
}
