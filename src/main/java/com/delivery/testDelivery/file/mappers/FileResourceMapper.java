package com.delivery.testDelivery.file.mappers;

import com.delivery.testDelivery.file.models.dtos.FileResourceDto;
import com.delivery.testDelivery.file.models.entities.FileResource;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileResourceMapper extends AbstractModelMapper<FileResource, FileResourceDto> {

    private ModelMapper modelMapper;

    @Autowired
    public FileResourceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FileResourceDto toDto(FileResource fileResource) {
        return modelMapper.map(fileResource, FileResourceDto.class);
    }

    @Override
    public FileResource toEntity(FileResourceDto fileResourceDto) {
        return modelMapper.map(fileResourceDto, FileResource.class);
    }

    @Override
    public List<FileResourceDto> toDtoList(List<FileResource> fileResourceList) {
        return fileResourceList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FileResource> toEntityList(List<FileResourceDto> fileResourceDtos) {
        return fileResourceDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
