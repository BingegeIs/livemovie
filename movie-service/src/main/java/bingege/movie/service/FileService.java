package bingege.movie.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String upload(InputStream target, String number, String fileName) throws IOException;

    InputStream read(String path) throws FileNotFoundException;

    String getRootPath();
}
