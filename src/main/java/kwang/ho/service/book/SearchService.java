package kwang.ho.service.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;

import java.util.List;

public interface SearchService {

    // 게시글 목록
    List<BookDto> selectSearchListWithPaging(PagingVO pagingVO) throws Exception;

    // 게시글 상세보기
    BookDto selectSearchDetail(int bid) throws Exception;

    // 게시글 갯수
    int selectSearchTotalCount(PagingVO pagingVO) throws Exception;
}
