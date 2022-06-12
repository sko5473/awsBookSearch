package kwang.ho.dto.board;

import lombok.Data;

@Data
public class CommentDto {

    private int cid;
    private int bid;
    private String content;
    private String writer;
    private String create_Date;
}
