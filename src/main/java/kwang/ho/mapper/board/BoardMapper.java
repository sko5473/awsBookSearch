package kwang.ho.mapper.board;

import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

        // 게시판 목록
        List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception;

        // 게시판 글쓰기
        void insertBoard(BoardDto board) throws Exception;

        // 게시판 상세보기
        BoardDto selectBoardDetail(int bid) throws Exception;

        // 게시판 삭제
        void boardDelete(int bid) throws Exception;

        // 게시판 수정페이지 호출
        BoardDto selectOpenBoardModify(int bid) throws Exception;

        // 게시판 수정
        void boardModify(BoardDto board) throws Exception;

        // 게시판 전체 갯수
        int selectBoardTotalCount() throws Exception;

        // 게시판 조회수
        void updateHitCount(int bid) throws Exception;

        // 게시판 답글 쓰기 페이지 호출
        BoardDto selectBoardReplyWrite(BoardDto board) throws Exception;

        // 게시판 답글 저장
        int boardReply(BoardDto boardDto) throws Exception;

        // 게시판 답글 순서 수정
        void updateBoardReplyStep(BoardDto boardDto) throws Exception;

        // 부모 reply_Level, reply_Step, bid_Parent 조회
        BoardDto selectParentBoard(BoardDto boardDto) throws Exception;

}
