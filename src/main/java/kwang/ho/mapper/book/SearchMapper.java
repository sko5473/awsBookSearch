package kwang.ho.mapper.book;

import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<BookDto> selectSearchListWithPaging(PagingVO pagingVO) throws Exception;

    BookDto selectSearchDetail(int bid) throws Exception;

    int selectSearchTotalCount(PagingVO pagingVO) throws Exception;

}
