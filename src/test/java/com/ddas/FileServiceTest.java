package com.ddas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.model.domain.File;
import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.repository.FileRepository;
import com.ddas.service.AuthService;
import com.ddas.service.FileService;
import com.ddas.service.TagService;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Test
    void uploadSuccessTest()
    {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(false);

        User user = new User("user@test.com", "12345678*iI", UserRole.USER);
        when(authService.getCurrentUser()).thenReturn(user);

        List<String> tags =  new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag2");

        fileService.upload(mockFile, "testName", "testDescription", tags);

        verify(fileRepository).save(any(File.class));
    }

    @Test
    void testUploadFileEmpty() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(true);

        List<String> tags =  new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag2");

        Exception exception = assertThrows(Exception.class, () -> 
        {
            fileService.upload(mockFile, "testName", "testDescription", tags);
        });

        assertEquals("File is empty or there is no file at all!", exception.getMessage());
    }

    @Test
    void testFindAllForUser() {
        String email = "user@test.com";
        User mockUser = mock(User.class);
        when(mockUser.getEmail()).thenReturn(email);
        when(authService.getCurrentUser()).thenReturn(mockUser);

        Page<File> expectedPage = new PageImpl<>(List.of(new File(), new File()));
        when(fileRepository.findAllByUserEmail(eq(email), any(Pageable.class))).thenReturn(expectedPage);

        Page<File> result = fileService.findAllForUser("name", "ASC", 0, 10);

        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(fileRepository).findAllByUserEmail(eq(email), any(Pageable.class));
    }

    @Test
    void testSearch() {
        User mockUser = mock(User.class);
        when(authService.getCurrentUser()).thenReturn(mockUser);

        Page<File> expectedPage = new PageImpl<>(List.of(new File(), new File()));
        when(fileRepository.findByUserAndSearchString(eq(mockUser), eq("test"), any(Pageable.class))).thenReturn(expectedPage);

        Page<File> result = fileService.search("test", "name", "DESC", 0, 5);

        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(fileRepository).findByUserAndSearchString(eq(mockUser), eq("test"), any(Pageable.class));
    }

    @Mock
    private AuthService authService;
    @Mock
    private TagService tagService;
    @Mock
    private FileRepository fileRepository;
    @InjectMocks
    private FileService fileService;
}
