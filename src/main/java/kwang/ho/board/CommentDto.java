package kwang.ho.board;

import lombok.Data;

@Data
public class CommentDto {

    private int seq;
    private int cid;
    private String content;
    private String writer;
    private String create_Date;
    private String delete_Yn;
}
