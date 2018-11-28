package bingege.movie.api.manage.user.payload;

import bingege.movie.model.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String nickname;
    private String username;
    private LocalDateTime latest;
    private LocalDateTime createAt;

    public static UserDTO valueOf(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .latest(user.getLatest())
                .createAt(user.getCreateAt())
                .build();
    }
}
