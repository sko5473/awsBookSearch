package kwang.ho.dto.book;

import lombok.Data;

@Data
public class BookAttachDto {

    private int bid; //도서 번호
    private String book_Name; //도서명
    private String author; //저자
    private String story; //줄거리
    private String publication_Date; //발간일
    private String location; //도서위치
    private String create_Date; //작성일
    private String creator_Id; //작성자
    /** 원본 파일명 */
    private String original_Name;
    /** 저장 파일명 */
    private String save_Name;
    /** 파일 크기 */
    private long sizee;
    /** 삭제 여부 */
    private String delete_Yn;
    /** 생성 시간 */
    private String insert_Time;
    /** 삭제 시간 */
    private String delete_Time;

}
