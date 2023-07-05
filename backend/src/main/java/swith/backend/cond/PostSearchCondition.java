package swith.backend.cond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchCondition {

    private Integer page;
    private Integer size;
    private String title;
    private String content;
}
