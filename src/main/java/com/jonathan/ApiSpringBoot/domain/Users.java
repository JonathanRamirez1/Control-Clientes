
package com.jonathan.ApiSpringBoot.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class Users implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    @NotEmpty
    @Column(name = "username")
    private String username;
    
    @NotEmpty
    @Column(name = "password")
    private String password;
    
    @OneToMany
    @JoinColumn(name = "id_user")
    private List<Rol> rols;
}
