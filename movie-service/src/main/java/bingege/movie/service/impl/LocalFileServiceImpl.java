package bingege.movie.service.impl;

import bingege.movie.service.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

/**
 * 本地文件上传服务
 *
 * @author ywb
 */
@Service
public class LocalFileServiceImpl implements FileService {

    private final static Logger logger = LoggerFactory.getLogger(LocalFileServiceImpl.class);

    @Value("${app.file.path}")
    private String rootPath;

    @Override
    public String upload(InputStream target, String number, String name) throws IOException {
        if (number == null) {
            throw new RuntimeException("Number can't be null");
        }
        File subFolder = getSubFolder(rootPath, LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());
        String fileName = number + "-" + System.currentTimeMillis() + getSuffix(name);
        File destination = new File(subFolder, fileName);
        FileUtils.copyInputStreamToFile(target, destination);
        return destination.getAbsolutePath();
    }

    @Override
    public InputStream read(String path) throws FileNotFoundException {
        String absolutePath = rootPath + path;
        logger.debug("read file that path is {}", absolutePath);
        File file = FileUtils.getFile(absolutePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Can't find the file that path is " + absolutePath);
        }
        return new FileInputStream(file);
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    private File getSubFolder(String root, int year, int monthValue) {
        String separator = System.getProperties().getProperty("file.separator");
        logger.debug("System current separator:{}", separator);
        String path = root + separator + year + separator + monthValue;
        logger.debug("request store getRootPath:{}", path);
        return existFolder(path);
    }

    private File existFolder(String rootPath) {
        File file = new File(rootPath);
        if (file.exists()) {
            logger.debug("request folder exist:{}", true);
            return file;
        }
        file.mkdirs();
        logger.debug("request folder had created");
        return new File(rootPath);
    }

    private String getSuffix(String name) {
        return name.substring(name.lastIndexOf("."));
    }
}
