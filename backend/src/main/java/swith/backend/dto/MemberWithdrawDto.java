package swith.backend.dto;

import javax.validation.constraints.NotBlank;

public class MemberWithdrawDto {
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String checkPassword;

    public MemberWithdrawDto(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}