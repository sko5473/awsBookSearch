package kwang.ho.service;

import kwang.ho.board.BoardDto;
import kwang.ho.board.CommentDto;
import kwang.ho.board.PagingVO;

import java.util.List;

public interface BoardService {

    // 게시글 목록
    List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception;

    // 게시글 상세보기
    BoardDto selectBoardDetail(int bid) throws Exception;

    // 게시글 등록
    void insertBoard(BoardDto board) throws Exception;

    // 게시글 삭제
    void boardDelete(int bid) throws Exception;

    // 게시글 수정페이지 호출
    BoardDto selectOpenBoardModify(int bid) throws Exception;

    // 게시글 수정
    void boardModify(BoardDto board) throws Exception;

    // 게시글 갯수
    int selectBoardTotalCount() throws Exception;

    // 게시판 답글 쓰기 페이지 이동
    BoardDto selectBoardReplyWrite(BoardDto board) throws Exception;

    // 게시판 답글 저장
    int boardReply(BoardDto boardDto) throws Exception;


}
