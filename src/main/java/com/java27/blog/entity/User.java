package com.java27.blog.entity;

import com.java27.blog.model.TypeUser;
import com.java27.blog.model.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name  = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(typeUser.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

}
