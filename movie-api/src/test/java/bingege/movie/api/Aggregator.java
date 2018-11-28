package bingege.movie.api;

import bingege.movie.service.FileService;
import bingege.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Aggregator {

    @Autowired
    public FileService fileService;
    @Autowired
    public UserService userService;

}