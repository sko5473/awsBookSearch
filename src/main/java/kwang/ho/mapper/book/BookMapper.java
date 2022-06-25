package kwang.ho.mapper.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper{

    // 게시판 목록
    List<BookDto> selectBookListWithPaging(PagingVO pagingVO) throws Exception;

    // 게시판 글쓰기
    void insertBook(BookDto bookDto) throws Exception;

    // 게시판 상세보기
    BookDto selectBookDetail(int bid) throws Exception;

    // 게시판 삭제
    void bookDelete(int bid) throws Exception;

    // 게시판 수정페이지 호출
    BookDto selectOpenBookModify(int bid) throws Exception;

    // 게시판 수정
    void bookModify(BookDto bookDto) throws Exception;

    // 게시판 전체 갯수
    int selectBookTotalCount() throws Exception;

    // 게시판 조회수
    void updateHitCount(int bid) throws Exception;

    // 부모 reply_Level, reply_Step, bid_Parent 조회
    BookDto selectParentBook(BookDto bookDto) throws Exception;

}
