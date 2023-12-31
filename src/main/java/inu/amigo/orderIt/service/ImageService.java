//package inu.amigo.orderIt.service;
//
//import inu.amigo.orderIt.domain.item.Image;
//import inu.amigo.orderIt.repository.ImageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ImageService {
//
//    private final ImageRepository imageRepository;
//
//    @Autowired
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//
//    public Optional<Image> getImageById(Long imageId) {
//        return imageRepository.findById(imageId);
//    }
//
////    /**
////     * 실제 이미지 저장 로적 (로컬 디렉토리에 저장)
////     * 저장 위치 = /static/images
////     * @param imageFile
////     * @return filePath
////     */
////    private String saveImage(MultipartFile imageFile) throws IOException {
////        Image image = new Image();
////
////        // 저장 위치를 /static/images 로 설정
////        String directory = "/static/images/";
////        String filename = image.getUuid().toString();
////
////        // 실제 저장 경로
////        String filePath = directory + filename;
////
////        // 저장 로직 구현
////        File file = new File(imageFile.getOriginalFilename());
////        file.createNewFile();
////        File
////
////        // 저장 경로 반환
////        return filePath;
////    }
////
////    public String add(HttpServletRequest request, MultipartFile file, BannerInput parameter) {
////
////        if (file != null) {
////            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
////            if (extension != null) {
////                FileUtil files = new FileUtil();
////                String imgPath = files.save(file).getUrlFilePath();
////                parameter.setImgPath(imgPath);
////            }
////        }
////    }
//
//    public void deleteImage(Long imageId) {
//        // 이미지 삭제 전 비즈니스 로직 추가 필요
//        imageRepository.deleteById(imageId);
//    }
//}
