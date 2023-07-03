package swith.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostSearch {
    private String title;
    private String writer;
}
