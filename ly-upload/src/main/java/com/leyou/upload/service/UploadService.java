package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gryant
 */
@Service
@Slf4j
public class UploadService {

    public static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/bmp");

    public String uploadImage(MultipartFile file) {

        try {
            // 文件类型校验
            if (!ALLOWED_TYPES.contains(file.getContentType())) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 文件内容校验
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 准备目标路径
            File dest = new File("F:\\乐优商城\\upload\\", file.getOriginalFilename());
            file.transferTo(dest);

            // 返回可访问地址
            return "http://image.leyou.com/image/12323e.gif";
        } catch (IOException e) {
            e.printStackTrace();
            throw new LyException(ExceptionEnum.UPLOAD_IMAGE_ERROR);
        }
    }
}
