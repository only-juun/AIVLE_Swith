package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import swith.backend.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostPagingDto {
    private int totalPageCount;
    private int currentPageNum;
    private long totalElementCount;
    private int currentPageElementCount;
    private List<PostSimpleDto> simpleLectureDtoList = new ArrayList<>();

    public PostPagingDto(Page<Post> searchResults) {
        this.totalPageCount = searchResults.getTotalPages();
        this.currentPageNum = searchResults.getNumber();
        this.totalElementCount = searchResults.getTotalElements();
        this.currentPageElementCount = searchResults.getNumberOfElements();
        this.simpleLectureDtoList = searchResults.getContent().stream()
                .map(PostSimpleDto::new)
                .collect(Collectors.toList());
    }
}