package swith.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdatePasswordDto {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String checkPassword;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String toBePassword;

    public UpdatePasswordDto(String checkPassword, String toBePassword) {
        this.checkPassword = checkPassword;
        this.toBePassword = toBePassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getToBePassword() {
        return toBePassword;
    }

    public void setToBePassword(String toBePassword) {
        this.toBePassword = toBePassword;
    }
}