package com.ddas.model.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails
{
    // TODO: Implement account locking etc. mechanisms 

    public User(String email, String password, UserRole role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() 
    { 
        return true;
    }

    @Override
    public boolean isAccountNonLocked() 
    { 
        return true; 
    }

    @Override 
    public boolean isCredentialsNonExpired() 
    { 
        return true; 
    }

    @Override
    public boolean isEnabled() 
    {
         return true; 
    }

    // Getters, setters
    public Long getId() { return id; }
    @Override public String getUsername() { return email; }
    @Override public String getPassword() { return password; }
    public UserRole getRole() { return role; }
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(UserRole role) { this.role = role; }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private String email; 
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}