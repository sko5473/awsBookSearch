package kwang.ho.mapper.board;

import kwang.ho.dto.board.AttachDTO;

import java.util.List;

public interface AttachMapper {

    public int insertAttach(List<AttachDTO> attachList);

    public AttachDTO selectAttachDetail(Long idx);

    public int deleteAttach(Long boardIdx);

    public List<AttachDTO> selectAttachList(Long boardIdx);

    public int selectAttachTotalCount(Long boardIdx);
}
