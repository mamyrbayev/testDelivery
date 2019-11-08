package com.delivery.testDelivery.file.services.impl;

import com.delivery.testDelivery.file.configurations.FileStorageProperties;
import com.delivery.testDelivery.file.exceptions.FileStorageException;
import com.delivery.testDelivery.file.exceptions.MyFileNotFoundException;
import com.delivery.testDelivery.file.models.entities.FileResource;
import com.delivery.testDelivery.file.repositories.FileResourceRepository;
import com.delivery.testDelivery.file.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path fileStorageLocation;
    private FileResourceRepository fileResourceRepository;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties,
                                  FileResourceRepository fileResourceRepository) {

        this.fileResourceRepository = fileResourceRepository;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            fileName = System.currentTimeMillis() + "_" + fileName;
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("FileResource not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("FileResource not found " + fileName, ex);
        }
    }

    public FileResource save(FileResource fileResource) {
        return fileResourceRepository.save(fileResource);
    }

    public boolean delete(FileResource fileResource) {
        Resource resource = loadFileAsResource(fileResource.getFileName());
        try {
            resource.getFile().delete();
        } catch (Exception e) {
            throw new MyFileNotFoundException("FileResource not found " + fileResource.getFileName());
        }
        fileResourceRepository.delete(fileResource);
        return true;
    }

    @Override
    public FileResource getFileByFileName(String fileName) throws MyFileNotFoundException {
        return  fileResourceRepository.getByFileName(fileName).orElse(null);
    }
}
