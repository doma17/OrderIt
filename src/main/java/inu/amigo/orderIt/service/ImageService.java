package inu.amigo.orderIt.service;

import inu.amigo.orderIt.exception.FileValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ImageService {

    @Value("${image.base-path}")
    private String imageBasePath;

    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png");

    // 이미지 최대 크기 10MB
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    /**
     * 이미지를 저장하고 저장된 이미지의 상대 경로를 반환하는 메서드
     *
     * @param imageFile 저장할 이미지 파일
     * @return imagePath 저장된 이미지의 상대 경로
     * @throws IOException 이미지 저장 중 예외 발생 시
     * @throws FileValidationException 이미지 유효성 검사 실패 시
     */
    public String saveImage(MultipartFile imageFile) throws IOException {
        validateImage(imageFile);

        String imagePath = saveImageLocal(imageFile);
        log.info("Image saved successfully. Path: {}", imagePath);

        return imagePath;
    }

    /**
     * 이미지 유효성을 검사하는 메서드
     *
     * @param imageFile 유효성을 검사할 이미지 파일
     * @throws FileValidationException 이미지 유효성 검사 실패 시
     */
    private void validateImage(MultipartFile imageFile) throws FileValidationException {
        if (imageBasePath == null) {
            log.error("ImageBasePath is null.");
            throw new FileValidationException("ImageBasePath is null.");
        }

        if (imageFile.isEmpty()) {
            log.error("Image file is empty.");
            throw new FileValidationException("Image file is empty.");
        }

        String fileExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            log.error("Invalid image extension. Allowed extensions: {}", ALLOWED_EXTENSIONS);
            throw new FileValidationException("Only " + String.join(", ", ALLOWED_EXTENSIONS) + " files are allowed.");
        }

        if (imageFile.getSize() > MAX_FILE_SIZE) {
            log.error("Image size exceeds the maximum limit (10MB).");
            throw new FileValidationException("File size exceeds the maximum limit (10MB).");
        }
        log.info("Image validation passed.");
    }
    /**
     * 이미지를 로컬에 저장하고, 저장된 이미지의 파일명을 반환하는 메서드
     *
     * @param imageFile 저장할 이미지 파일
     * @return 저장된 이미지의 파일명
     * @throws IOException 이미지 저장 중 예외 발생 시
     */
    private String saveImageLocal(MultipartFile imageFile) throws IOException {
        String uploadDir = imageBasePath;
        File uploadPath = new File(uploadDir);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
            log.debug("Image directory does not exist. Creating directory.");
        }

        return saveFile(imageFile, uploadPath);
    }

    /**
     * 이미지 파일을 서버에 저장하고, 저장된 이미지의 파일명을 반환하는 메서드
     *
     * @param imageFile  저장할 이미지 파일
     * @param uploadPath 이미지를 저장할 경로
     * @return 저장된 이미지의 파일명
     * @throws IOException 이미지 저장 중 예외 발생 시
     */
    private String saveFile(MultipartFile imageFile, File uploadPath) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));

        Path imagePath = Path.of(uploadPath.getAbsolutePath() + File.separator + fileName);
        Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        log.info("File saved successfully. Name: {}", fileName);
        return fileName;
    }
}
