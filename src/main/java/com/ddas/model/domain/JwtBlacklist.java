package com.ddas.model.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "jwt_blacklist")
public class JwtBlacklist
{
    public JwtBlacklist(String token, Date expirationDate)
    {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Date expirationDate;
}
