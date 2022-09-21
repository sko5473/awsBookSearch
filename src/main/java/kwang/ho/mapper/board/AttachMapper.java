package kwang.ho.mapper.board;

import kwang.ho.dto.board.AttachDTO;

import java.util.List;

public interface AttachMapper {

    int insertAttach(List<AttachDTO> attachList);

    AttachDTO selectAttachDetail(Long idx);

    int deleteAttach(int boardIdx);

    List<AttachDTO> selectAttachList(int bid);

    int selectAttachTotalCount(int bid);
}
