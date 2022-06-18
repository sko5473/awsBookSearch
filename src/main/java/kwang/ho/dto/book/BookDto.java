package kwang.ho.dto.book;

import lombok.Data;

@Data
public class BookDto {

    private int bid; //도서 번호
    private String book_Name; //도서명
    private String author; //저자
    private String story; //줄거리
    private String publication_Date; //발간일
    private String location; //도서위치
    private String create_Date; //작성일
    private String creator_Id; //작성자

}
