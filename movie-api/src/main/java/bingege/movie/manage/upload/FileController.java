package bingege.movie.manage.upload;

import bingege.movie.common.api.ApiResponse;
import bingege.movie.common.config.security.CurrentUser;
import bingege.movie.common.exception.AppException;
import bingege.movie.common.exception.ResourceNotFoundException;
import bingege.movie.common.exception.UnsupportedMediaTypeException;
import bingege.movie.common.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@Api(value = "/api/file", tags = "文件上传")
@Validated
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String PROPERTY = System.getProperties().getProperty("file.separator");

    @Autowired
    FileService fileService;

    @ApiOperation(value = "图片上传")
    @PostMapping(
            value = "/upload",
            consumes = {"multipart/*"},
            headers = "content-type=multipart/form-data")
    public ApiResponse<String> upload(
            @ApiParam(value = "上传的图片）", required = true)
            @RequestParam("file") MultipartFile file) {
        if (!checkMiniAppImageContentType(file.getContentType())) {
            throw new UnsupportedMediaTypeException("只支持png/jpg格式的文件");
        }
        try {
            String upload = fileService.upload(file.getInputStream(), "", file.getOriginalFilename());
            logger.debug("upload:{}", upload);
            String rootPath = fileService.getRootPath();
            logger.debug("root path:{}", rootPath);
            String path = upload.substring(upload.lastIndexOf(rootPath) + rootPath.length());
            logger.debug("return path:{}", path);
            return ApiResponse.create(path);
        } catch (IOException e) {
            throw new AppException("文件上传失败", e);
        } catch (StringIndexOutOfBoundsException e) {
            throw new AppException("文件名字格式错误", e);
        }
    }

    @ApiOperation(value = "查看图片")
    @GetMapping("/image/{year}/{month}/{fileName}")
    public void image(@PathVariable("year") String year,
                      @PathVariable("month") String month,
                      @PathVariable("fileName") String fileName,
                      HttpServletResponse response) {
        String path = PROPERTY + year + PROPERTY + month + PROPERTY + fileName;
        response.setContentType(convertSuffix(fileName));
        try {
            IOUtils.copy(fileService.read(path), response.getOutputStream());
        } catch (IOException e) {
            throw new ResourceNotFoundException("image", "path", e);
        }
    }

    private String convertSuffix(String fileName) {
        String suffix = getSuffix(fileName);
        switch (suffix) {
            case ".jpg":
                return MediaType.IMAGE_JPEG_VALUE;
            case ".png":
                return MediaType.IMAGE_PNG_VALUE;
            case ".jpeg":
                return MediaType.IMAGE_JPEG_VALUE;
            default:
                throw new UnsupportedMediaTypeException("只支持png/jpg格式的文件");
        }
    }

    private boolean checkMiniAppImageContentType(String contentType) {
        if (contentType == null) {
            return false;
        } else {
            return contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/jpg");
        }
    }

    private String getSuffix(String name) {
        return name.substring(name.lastIndexOf("."));
    }
}