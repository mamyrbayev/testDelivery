package com.delivery.testDelivery.file.models.dtos;

import com.delivery.testDelivery.models.dtos.base.BaseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResourceDto extends BaseDto {

    private String fileName;

    private String fileDownloadUri;

    private String fileType;

    private Long size;

}
