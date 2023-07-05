package swith.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostSearch {
    private String type;
    private String content;
}
