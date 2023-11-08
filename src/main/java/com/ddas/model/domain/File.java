package com.ddas.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class File
{
    public File(User user, String name, String description, byte[] data, Long size)
    {
        this.user = user;
        this.name = name;
        this.description = description;
        this.data = data;
        this.size = size;
        this.lastModified = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private Long size;
    private String lastModified;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
    @ManyToOne(optional = false)
    private User user;
}
