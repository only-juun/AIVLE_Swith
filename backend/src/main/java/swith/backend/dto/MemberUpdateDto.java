package swith.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

public class MemberUpdateDto {
    private Optional<String> name;
    private Optional<String> nickname;

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getNickname() {
        return nickname;
    }

    public void setNickName(Optional<String> nickname) {
        this.nickname = nickname;
    }

    public MemberUpdateDto(Optional<String> name, Optional<String> nickname) {
        this.name = name;
        this.nickname = nickname;
    }
}

