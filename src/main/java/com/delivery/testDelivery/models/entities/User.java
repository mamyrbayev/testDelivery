package com.delivery.testDelivery.models.entities;

import com.delivery.testDelivery.models.audits.AuditModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_users",
        initialValue = 1,
        allocationSize = 1
)
public class User extends AuditModel {

    @Column(name = "first_name")
    @NotNull(message = "first_name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "last_name is required")
    private String lastName;

    @Column(name = "phone_number")
    @NotNull(message = "phone_number is required")
    private String phoneNumber;

    @Column(unique = true, name = "login")
    @NotNull(message = "login is required")
    private String login;

    @Column(unique = true, name = "address")
    private String address;

    @NotNull(message = "password is required")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false, updatable = false)
    private Date birthday;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @NotNull(message = "role is required")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Role role;

}
