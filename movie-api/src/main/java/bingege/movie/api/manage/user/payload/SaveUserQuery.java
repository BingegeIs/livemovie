package bingege.movie.api.manage.user.payload;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class SaveUserQuery {

    @NotBlank
    private String nickname;
}
