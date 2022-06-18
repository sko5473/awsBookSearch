package kwang.ho.service.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import kwang.ho.mapper.book.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    // 게시글 목록
    @Override
    public List<BookDto> selectBookListWithPaging(PagingVO pagingVO) throws Exception {
        return bookMapper.selectBookListWithPaging(pagingVO);
    }

    // 게시글 상세보기
    @Override
    public BookDto selectBookDetail(int bid) throws Exception {
        return bookMapper.selectBookDetail(bid);
    }

    // 게시글 등록
    @Override
    public void insertBook(BookDto bookDto) throws Exception {

        bookMapper.insertBook(bookDto);
    }

    // 게시글 삭제
    @Override
    public void bookDelete(int bid) throws Exception {
        bookMapper.bookDelete(bid);
    }

    // 게시글 수정페이지 호출
    @Override
    public BookDto selectOpenBookModify(int bid) throws Exception {
        return bookMapper.selectOpenBookModify(bid);
    }

    // 게시글 수정
    @Override
    public void bookModify(BookDto bookDto) throws Exception {
        bookMapper.bookModify(bookDto);
    }

    // 게시글 갯수
    @Override
    public int selectBookTotalCount() throws Exception {
        return bookMapper.selectBookTotalCount();
    }
}
