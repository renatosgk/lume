package com.gs.global_solution.model;


import com.gs.global_solution.enums.NivelDeEstresse;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usuario_seq_generator")
    @SequenceGenerator(name = "usuario_seq_generator",sequenceName = "usuario_seq",allocationSize=1)
    private Long id;
    @Column(name = "nm",nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer idade;
    @Enumerated(EnumType.STRING)
    @Column(name = "nvl_de_estresse")
    private NivelDeEstresse nivelDeEstresse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
