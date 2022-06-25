package kwang.ho.service.board;

import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.mapper.board.BoardMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    // 게시글 목록
    @Override
    public List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception {
        return boardMapper.selectBoardListWithPaging(pagingVO);
    }

    // 게시글 상세보기
    @Override
    public BoardDto selectBoardDetail(int bid) throws Exception {
        boardMapper.updateHitCount(bid);
        return boardMapper.selectBoardDetail(bid);
    }

    // 게시글 등록
    @Override
    public void insertBoard(BoardDto board) throws Exception {

        boardMapper.insertBoard(board);
    }

    // 게시글 삭제
    @Override
    public void boardDelete(int bid) throws Exception {
        boardMapper.boardDelete(bid);
    }

    // 게시글 수정페이지 호출
    @Override
    public BoardDto selectOpenBoardModify(int bid) throws Exception {
        return boardMapper.selectOpenBoardModify(bid);
    }

    // 게시글 수정
    @Override
    public void boardModify(BoardDto board) throws Exception {
        boardMapper.boardModify(board);
    }

    // 게시글 갯수
    @Override
    public int selectBoardTotalCount() throws Exception {
        return boardMapper.selectBoardTotalCount();
    }

    // 게시판 답글 등록 페이지 호출
    @Override
    public BoardDto selectBoardReplyWrite(BoardDto board) throws Exception {
        return boardMapper.selectBoardReplyWrite(board);
    }

    // 게시글 답글 등록
    @Override
    public int boardReply(BoardDto boardDto) throws Exception {

        BoardDto parent = boardMapper.selectParentBoard(boardDto);
        boardMapper.updateBoardReplyStep(parent);
        boardDto.setBid_Parent(parent.getBid_Parent());
        boardDto.setReply_Step(parent.getReply_Step()+1);
        boardDto.setReply_Level(parent.getReply_Level()+1);

        return boardMapper.boardReply(boardDto);
    }

}
