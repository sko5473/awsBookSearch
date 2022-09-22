package kwang.ho.mapper.board;

import kwang.ho.dto.board.AttachDTO;

import java.util.List;

public interface AttachMapper {

    int insertAttach(List<AttachDTO> attachList);

    int modifyAttach(List<AttachDTO> attachList);

    AttachDTO selectAttachDetail(Long idx);

    int deleteAttach(int boardIdx);

    List<AttachDTO> selectAttachList(int bid);

    int selectAttachTotalCount(int bid);

    int undeleteAttach(List<Long> idxs);

    // 게시물 수정버튼 클릭시 조회된 첨부파일 개수 리턴
    int selectFileListCount(int bid);
}
