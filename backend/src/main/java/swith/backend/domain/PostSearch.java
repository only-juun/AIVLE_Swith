package swith.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PostSearch {
    private String title;
    private String writer;
}
