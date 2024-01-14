package com.ddas.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ddas.model.domain.File;
import com.ddas.model.domain.Tag;
import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.repository.FileRepository;
import com.ddas.repository.TagRepository;
import com.ddas.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner
{
    @Transactional
    public void run(ApplicationArguments args)
    {
        User user1 = new User("user@test.com", passwordEncoder.encode("12345678*iI"), UserRole.USER);
        User user2 = new User("user@test2.com", passwordEncoder.encode("12345678*iI"), UserRole.USER);
        userRepository.save(user1);
        userRepository.save(user2);

        Tag tag1 = new Tag("WAZNE");
        Tag tag2 = new Tag("PRACA");
        Tag tag3 = new Tag("UCZELNIA");
        Tag tag4 = new Tag("MOJE");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);
        tagRepository.save(tag4);

        Set<Tag> fileTags1 = new HashSet<>();
        fileTags1.add(tag1);
        fileTags1.add(tag2);

        Set<Tag> fileTags2 = new HashSet<>();
        fileTags2.add(tag3);
        fileTags2.add(tag4);

        Set<Tag> fileTags3 = new HashSet<>();
        fileTags3.add(tag1);

        byte[] fileData1 = readFileData("documents/android_test.pdf");
        byte[] fileData2 = readFileData("documents/android_test.pdf");
        byte[] fileData3 = readFileData("documents/android_test.pdf");
        byte[] fileData4 = readFileData("documents/android_test.pdf");
        byte[] fileData5 = readFileData("documents/android_test.pdf");
        byte[] fileData6 = readFileData("documents/android_test.pdf");
        byte[] fileData7 = readFileData("documents/android_test.pdf");

        File file1 = new File(user1, "Plik testowy1", "Opis pliku testowego1", fileData1, (long) fileData1.length, fileTags1);
        File file2 = new File(user1, "Plik testowy2", "Opis pliku testowego2", fileData2, (long) fileData2.length, fileTags2);
        File file3 = new File(user1, "Plik testowy3", "Opis pliku testowego3", fileData3, (long) fileData3.length, fileTags3);
        File file4 = new File(user1, "Plik testowy4", "Opis pliku testowego4", fileData4, (long) fileData4.length, fileTags1);
        File file5 = new File(user2, "Plik testowy5", "Opis pliku testowego5", fileData5, (long) fileData5.length, fileTags2);
        File file6 = new File(user2, "Plik testowy6", "Opis pliku testowego6", fileData6, (long) fileData6.length, fileTags3);
        File file7 = new File(user2, "Plik testowy7", "Opis pliku testowego7", fileData7, (long) fileData7.length, fileTags1);
        fileRepository.save(file1);
        fileRepository.save(file2);
        fileRepository.save(file3);
        fileRepository.save(file4);
        fileRepository.save(file5);
        fileRepository.save(file6);
        fileRepository.save(file7);
    }

     private byte[] readFileData(String filePath)
     {
        try
        {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } 
        catch (IOException e)
        {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
}

