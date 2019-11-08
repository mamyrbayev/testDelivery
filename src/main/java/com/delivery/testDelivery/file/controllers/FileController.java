package com.delivery.testDelivery.file.controllers;

import com.delivery.testDelivery.controllers.BaseController;
import com.delivery.testDelivery.exceptions.ServiceException;
import com.delivery.testDelivery.file.exceptions.MyFileNotFoundException;
import com.delivery.testDelivery.file.mappers.FileResourceMapper;
import com.delivery.testDelivery.file.models.dtos.FileResourceDto;
import com.delivery.testDelivery.file.models.entities.FileResource;
import com.delivery.testDelivery.file.services.FileStorageService;
import com.delivery.testDelivery.shared.utils.responses.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileController extends BaseController {

    @Value("${image.not.found.src}")
    public String NO_IMAGE;

    public FileResourceMapper fileResourceMapper;
    private FileStorageService fileStorageService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public FileController(FileResourceMapper fileResourceMapper, FileStorageService fileStorageService) {
        this.fileResourceMapper = fileResourceMapper;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploadFile")
    @ApiOperation("Залить файл")
    @ResponseBody
    public FileResourceDto uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path("downloadFile/")
                .path(fileName)
                .toUriString();

        FileResourceDto fileResourceDto = FileResourceDto.builder()
                .fileDownloadUri(fileDownloadUri)
                .fileName(fileName)
                .size(file.getSize())
                .fileType(file.getContentType())
                .build();

        FileResource fileResource = fileStorageService.save(fileResourceMapper.toEntity(fileResourceDto));
        return fileResourceMapper.toDto(fileResource);
    }

    @PostMapping("/uploadMultipleFiles")
    @ApiOperation("Залить все файлы")
    public List<FileResourceDto> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    @ApiOperation("Скачать файл")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/get/{fileName:.+}")
    @ApiOperation("Скачать файл")
    public ResponseEntity<byte[]> showFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(Files.readAllBytes(resource.getFile().toPath()));
    }

    @GetMapping(value = "/no-image")
    @ApiOperation("Фотография по умолчанию")
    public @ResponseBody
    ResponseEntity<byte[]> getFile() throws IOException {
        File file = new ClassPathResource(NO_IMAGE).getFile();

        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        MediaType mediaType = null;
        if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE)) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
            mediaType = MediaType.IMAGE_PNG;
        }
        return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(file.toPath()));
    }

    @DeleteMapping("/deleteFile/{fileName:.+}")
    @ApiOperation("Удаление файла")
    public ResponseEntity<?> delete(@PathVariable String fileName) throws ServiceException, MyFileNotFoundException {
        FileResource fileResource = (fileStorageService.getFileByFileName(fileName));
        if (fileResource != null) {
            fileStorageService.delete(fileResource);
        } else {
            throw new MyFileNotFoundException("file with name " + fileName + " not found");
        }
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }


}
