package com.jonathan.ApiSpringBoot.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "person")
@NoArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPerson;

    @NotEmpty //Validacion para String
    @Column(name = "nombre")
    private String name;

    @NotEmpty
    @Column(name = "apellido")
    private String lastName;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "telefono")
    private String phone;

    @NonNull
    @Column(name = "saldo")
    private Double amount;
}
