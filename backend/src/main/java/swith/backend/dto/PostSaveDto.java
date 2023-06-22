//package swith.backend.dto;
//
//import swith.backend.domain.Post;
//
//import javax.validation.constraints.NotBlank;
//
//public class PostSaveDto(@NotBlank(message = "제목을 입력해주세요.") String title,
//        @NotBlank(message = "내용을 입력해주세요.") String content{
//
//    public Post toEntity() {
//        return Post.builder().title(title).content(content).build();
//        }
//}
//
//
