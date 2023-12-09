package com.ddas.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class File
{
    public File(User user, String name, String description, byte[] data, Long size, Set<Tag> tags)
    {
        this.user = user;
        this.name = name;
        this.description = description;
        this.data = data;
        this.size = size;
        this.lastModified = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.tags = tags;
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
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "file_tag",
        joinColumns = @JoinColumn(name = "file_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
