package kwang.ho.board;

import lombok.Data;

@Data
public class BoardDto {
    private int bid;
    private String title;
    private String contents;
    private int hit_Cnt;
    private String creator_Id;
    private String created_Datetime;
    private String updater_Id;
    private String updated_Datetime;
}
