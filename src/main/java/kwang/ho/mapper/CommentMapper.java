package kwang.ho.mapper;

import kwang.ho.board.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 갯수
    public int commentCount() throws Exception;

    // 댓글 목록
    public List<CommentDto> commentList(int bid) throws Exception;

    // 댓글 작성
    public int commentInsert(CommentDto commentDto) throws Exception;

    // 댓글 수정
    public int commentUpdate(CommentDto commentDto) throws Exception;

    // 댓글 삭제
    public int commentDelete(int cid) throws Exception;

}
