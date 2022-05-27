package kwang.ho.mapper;

import kwang.ho.board.BoardDto;
import kwang.ho.board.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

        List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception;

        int selectBoardTotalCount() throws Exception;

        void insertBoard(BoardDto board) throws Exception;

        void updateHitCount(int bid) throws Exception;

        BoardDto selectBoardDetail(int bid) throws Exception;

        void boardDelete(int bid) throws Exception;

        BoardDto selectOpenBoardModify(int bid) throws Exception;

        void boardModify(BoardDto board) throws Exception;

}
