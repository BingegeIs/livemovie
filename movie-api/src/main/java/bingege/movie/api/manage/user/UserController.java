package bingege.movie.api.manage.user;

import bingege.movie.api.manage.user.payload.AddUserQuery;
import bingege.movie.api.manage.user.payload.SaveUserQuery;
import bingege.movie.api.manage.user.payload.UserDTO;
import bingege.movie.common.api.ApiResponse;
import bingege.movie.common.api.PageQuery;
import bingege.movie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "后台管理 - 管理员")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    @ApiOperation("注册管理员")
    public ApiResponse add(@RequestBody AddUserQuery query) {
        return ApiResponse.create(userService.add(query.getNickname(), query.getUsername(), query.getPassword()));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询管理员")
    public ApiResponse gets(PageQuery query) {
        return ApiResponse.createPages(userService.gets(query));
    }

    @GetMapping("/{id}")
    @ApiOperation("管理员详情")
    public ApiResponse get(@PathVariable("id") Long id) {
        return ApiResponse.create(UserDTO.valueOf(userService.get(id)));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新管理员信息")
    public ApiResponse save(@PathVariable("id") Long id,
                            SaveUserQuery query) {
        userService.save(id, query.getNickname());
        return ApiResponse.create(true);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除一个管理员")
    public ApiResponse delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ApiResponse.create(true);
    }
}
