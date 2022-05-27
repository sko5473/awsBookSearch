package kwang.ho.service;

import kwang.ho.board.BoardDto;
import kwang.ho.board.PagingVO;

import java.util.List;

public interface BoardService {


    List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception;

    int selectBoardTotalCount() throws Exception;

    void insertBoard(BoardDto board) throws Exception;

    BoardDto selectBoardDetail(int bid) throws Exception;

    void boardDelete(int bid) throws Exception;

    BoardDto selectOpenBoardModify(int bid) throws Exception;

    void boardModify(BoardDto board) throws Exception;
}
