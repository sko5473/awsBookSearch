package kwang.ho.service.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import kwang.ho.mapper.book.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SearchMapper searchMapper;

    // 게시글 목록
    @Override
    public List<BookDto> selectSearchListWithPaging(PagingVO pagingVO) throws Exception {
        return searchMapper.selectSearchListWithPaging(pagingVO);
    }

    // 게시글 상세보기
    @Override
    public BookDto selectSearchDetail(int bid) throws Exception {
        return searchMapper.selectSearchDetail(bid);
    }

    // 게시글 갯수
    @Override
    public int selectSearchTotalCount(PagingVO pagingVO) throws Exception {
        return searchMapper.selectSearchTotalCount(pagingVO);
    }

}
