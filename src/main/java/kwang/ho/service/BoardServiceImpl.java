package kwang.ho.service;

import kwang.ho.board.BoardDto;
import kwang.ho.board.PagingVO;
import kwang.ho.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception {
        return boardMapper.selectBoardListWithPaging(pagingVO);
    }

    @Override
    public int selectBoardTotalCount() throws Exception {
        return boardMapper.selectBoardTotalCount();
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception {
        boardMapper.insertBoard(board);
    }

    @Override
    public BoardDto selectBoardDetail(int bid) throws Exception {
        boardMapper.updateHitCount(bid);
        return boardMapper.selectBoardDetail(bid);
    }

    @Override
    public void boardDelete(int bid) throws Exception {
        boardMapper.boardDelete(bid);
    }

    @Override
    public void boardModify(BoardDto board) throws Exception {
        boardMapper.boardModify(board);
    }

    @Override
    public BoardDto selectOpenBoardModify(int bid) throws Exception {
        return boardMapper.selectOpenBoardModify(bid);
    }


}
