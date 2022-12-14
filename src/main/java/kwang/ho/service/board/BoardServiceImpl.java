package kwang.ho.service.board;

import kwang.ho.common.FileUtils;
import kwang.ho.dto.board.BoardDto;
import kwang.ho.dto.board.AttachDTO;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.mapper.board.AttachMapper;
import kwang.ho.mapper.board.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private AttachMapper attachMapper;

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

       // }
    }

    // 게시글 등록(첨부파일 추가)
    @Override
    public boolean insertBoard(BoardDto board, MultipartFile[] files) throws Exception {
        int queryResult = 1;

       /* if (insertBoard(board) == false) {
            return false;
        }*/
        boardMapper.insertBoard(board);
        List<AttachDTO> fileList = fileUtils.uploadFiles(files, board.getBid());
        if (!CollectionUtils.isEmpty(fileList)) {
            
            for(AttachDTO data: fileList){
                System.out.println("data = " + data);
            }

            queryResult = attachMapper.insertAttach(fileList);
            if (queryResult < 1) {
                queryResult = 0;
            }
        }

        return (queryResult > 0);
    }

    @Override
    public List<AttachDTO> getAttachFileList(int bid) {

        int fileTotalCount = attachMapper.selectAttachTotalCount(bid);
        if (fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return attachMapper.selectAttachList(bid);
    }

    @Override
    public int selectFileListCount(int bid) {

        return attachMapper.selectFileListCount(bid);
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
    public void boardModify(BoardDto board, MultipartFile[] files) throws Exception {


        boardMapper.boardModify(board);

        // 파일이 추가, 삭제, 변경된 경우
        if ("Y".equals(board.getChangeYn())) {
            attachMapper.deleteAttach(board.getBid());

            // fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 'N'으로 업데이트
            if (!CollectionUtils.isEmpty(board.getFileIdxs())) {
                attachMapper.undeleteAttach(board.getFileIdxs());
            }
        }

        List<AttachDTO> fileList = fileUtils.uploadFiles(files, board.getBid());
        if (!CollectionUtils.isEmpty(fileList)) {
            attachMapper.modifyAttach(fileList);

            for(AttachDTO data: fileList){
                System.out.println("data = " + data);
            }
        }
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

    @Override
    public AttachDTO getAttachDetail(int idx) {
        return attachMapper.selectAttachDetail(idx);
    }

}
