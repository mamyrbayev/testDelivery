package com.delivery.testDelivery.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    @ApiModelProperty(notes = "Имя", readOnly = true)
    private String firstName;

    @ApiModelProperty(notes = "Фамилия", readOnly = true)
    private String lastName;

    @ApiModelProperty(notes = "Мобильный номер", readOnly = true)
    private String phoneNumber;

    @ApiModelProperty(notes = "Логин", readOnly = true)
    private String login;

    @ApiModelProperty(notes = "Адрес", readOnly = true)
    private String address;

    @ApiModelProperty(notes = "Роль", readOnly = true)
    private RoleDto role;

    @ApiModelProperty(notes = "Дата рожения", readOnly = true)
    private Date birthday;

    @ApiModelProperty(notes = "Активный", readOnly = true)
    private Boolean isActive;

    @ApiModelProperty(notes = "isLoggedIn", readOnly = true)
    private Boolean isLoggedIn = false;

    @ApiModelProperty(notes = "isSignedUp", readOnly = true)
    private Boolean isSignedUp = false;

    @ApiModelProperty(notes = "productTitleSearched", readOnly = true)
    private String productTitleSearched = "";
}
