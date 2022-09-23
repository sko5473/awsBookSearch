package kwang.ho.dto.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachDTO {

    /** 파일 번호 (PK) */
    private int idx;

    /** 게시글 번호 (FK) */
    private int board_Idx;

    /** 원본 파일명 */
    private String original_Name;

    /** 저장 파일명 */
    private String save_Name;

    /** 파일 크기 */
    private long sizee;

    /** 삭제 여부 */
    private String delete_Yn;

    /** 생성 시간 */
    private LocalDateTime insert_Time;

    /** 삭제 시간 */
    private String delete_Time;
}
