package kwang.ho.service.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;

import java.util.List;

public interface BookService {

    // 게시글 목록
    List<BookDto> selectBookListWithPaging(PagingVO pagingVO) throws Exception;

    // 게시글 상세보기
    BookDto selectBookDetail(int bid) throws Exception;

    // 게시글 등록
    void insertBook(BookDto bookDto) throws Exception;

    // 게시글 삭제
    void bookDelete(int bid) throws Exception;

    // 게시글 수정페이지 호출
    BookDto selectOpenBookModify(int bid) throws Exception;

    // 게시글 수정
    void bookModify(BookDto bookDto) throws Exception;

    // 게시글 갯수
    int selectBookTotalCount() throws Exception;

}
