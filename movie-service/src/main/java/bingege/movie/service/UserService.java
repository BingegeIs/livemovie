package bingege.movie.service;

import bingege.movie.common.api.PageQuery;
import bingege.movie.model.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    Optional<User> getUserByUsername(String username);

    /**
     * 初始化一个管理员
     *
     * @return
     */
    Optional<User> initAdmin();

    /**
     * 更新登录时间
     *
     * @param username
     */
    void fresh(String username);

    User add(String nickname, String username, String password);

    Page<User> gets(PageQuery query);

    void save(Long id, String nickname);

    User get(Long id);

    void delete(Long id);
}
