package com.delivery.testDelivery.models.mappers;

import com.delivery.testDelivery.models.dtos.CategoryDto;
import com.delivery.testDelivery.models.dtos.RoleDto;
import com.delivery.testDelivery.models.entities.Role;
import com.delivery.testDelivery.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoleMapper extends AbstractModelMapper<Role, RoleDto> {

    private ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> roles) {
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> roleDtos) {
        return roleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
