package inu.amigo.orderIt.service;

import inu.amigo.orderIt.exception.FileValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {"image.base-path=static/images"})
class ImageServiceTest {

    @Value("${image.base-path}")
    private String imageBasePath;

    @InjectMocks
    private ImageService imageService;

    @Mock
    private MockMultipartFile imageFile;

    @BeforeEach
    void setUp() throws IOException {
        // 가상의 이미지 파일 생성
        imageFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "image content".getBytes());
    }

    @Test
    void saveImage_WithValidImage_ShouldNotThrowException() {
        // When/Then
        assertDoesNotThrow(() -> imageService.saveImage(imageFile));
    }

    @Test
    void saveImage_WithEmptyImage_ShouldThrowFileValidationException() {
        // Given
        when(imageFile.isEmpty()).thenReturn(true);

        // When/Then
        FileValidationException exception = assertThrows(FileValidationException.class, () -> imageService.saveImage(imageFile));
        assertEquals("Image file is empty.", exception.getMessage());
    }

    @Test
    void saveImage_WithInvalidExtension_ShouldThrowFileValidationException() {
        // Given
        when(imageFile.isEmpty()).thenReturn(false);
        when(StringUtils.getFilenameExtension(imageFile.getOriginalFilename())).thenReturn("txt");

        // When/Then
        FileValidationException exception = assertThrows(FileValidationException.class, () -> imageService.saveImage(imageFile));
        assertEquals("Only jpg, jpeg, png files are allowed.", exception.getMessage());
    }

    @Test
    void saveImage_WithExceedingFileSize_ShouldThrowFileValidationException() {
        // Given
        when(imageFile.isEmpty()).thenReturn(false);
        when(StringUtils.getFilenameExtension(imageFile.getOriginalFilename())).thenReturn("jpg");
        when(imageFile.getSize()).thenReturn(ImageService.MAX_FILE_SIZE + 1);

        // When/Then
        FileValidationException exception = assertThrows(FileValidationException.class, () -> imageService.saveImage(imageFile));
        assertEquals("File size exceeds the maximum limit (10MB).", exception.getMessage());
    }
}
