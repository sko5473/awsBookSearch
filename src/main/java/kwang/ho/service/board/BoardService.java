package kwang.ho.service.board;

import kwang.ho.dto.board.AttachDTO;
import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.PagingVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {

    // 게시글 목록
    List<BoardDto> selectBoardListWithPaging(PagingVO pagingVO) throws Exception;

    // 게시글 상세보기
    BoardDto selectBoardDetail(int bid) throws Exception;

    // 게시글 등록
    void insertBoard(BoardDto board) throws Exception;

    boolean insertBoard(BoardDto board, MultipartFile[] files) throws Exception;

    // 게시글 첨부파일 리스트 가져오기
    List<AttachDTO> getAttachFileList(int bid);

    // 게시글 조회시 첨부파일 리스트 개수 가져오기
    int selectFileListCount(int bid);

    // 게시글 삭제
    void boardDelete(int bid) throws Exception;

    // 게시글 수정페이지 호출
    BoardDto selectOpenBoardModify(int bid) throws Exception;

    // 게시글 수정
    void boardModify(BoardDto board, MultipartFile[] files) throws Exception;

    // 게시글 갯수
    int selectBoardTotalCount() throws Exception;

    // 게시판 답글 쓰기 페이지 이동
    BoardDto selectBoardReplyWrite(BoardDto board) throws Exception;

    // 게시판 답글 저장
    int boardReply(BoardDto boardDto) throws Exception;

    //첨부파일idx로 첨부파일 상세조회
    AttachDTO getAttachDetail(int idx);
}
