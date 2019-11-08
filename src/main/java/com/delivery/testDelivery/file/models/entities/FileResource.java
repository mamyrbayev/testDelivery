package com.delivery.testDelivery.file.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "file_resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_file_resources",
        initialValue = 1,
        allocationSize = 1
)
public class FileResource extends AuditModel {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_download_uri")
    private String fileDownloadUri;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "size")
    private Long size;

}
