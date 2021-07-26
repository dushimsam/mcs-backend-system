package com.example.mount_carmel_school.model;

import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.model.global.Auditable;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")

public class User extends Auditable<String> implements UserDetails{
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    private Long id;

    @NotNull
    @Column(nullable =false)
    private String firstName;


    private String profile = "";

    @NotNull
    @Column(nullable =false)
    private String lastName;

    @NotNull
    @Column(unique=true,nullable = false)
    private String userName;

    @NotNull
    private Boolean isLocked = false;

    @NotNull
    private Boolean isConfirmed = false;

    @NotNull
    @Column(nullable = false)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable =false)
    private String gender;

    @NotNull
    @Column(unique=true,nullable = false)
    private String email;


    @NotNull
    @Enumerated(EnumType.STRING)
    private UserCategory category;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(category.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
