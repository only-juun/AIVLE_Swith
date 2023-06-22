package swith.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Member;

@Data
@NoArgsConstructor
public class MemberInfoDto {

    private String name;
    private String nickname;
    private String username;

    @Builder
    public MemberInfoDto(Member member) {
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.username = member.getUsername();
    }
}
