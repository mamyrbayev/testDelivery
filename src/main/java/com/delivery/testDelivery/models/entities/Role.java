package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_roles",
        initialValue = 1,
        allocationSize = 1
)
public class Role extends AuditModel {

    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_USER = 2L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_TEACHER_NAME = "ROLE_USER";

    @Column(unique = true)
    private String name;
}
