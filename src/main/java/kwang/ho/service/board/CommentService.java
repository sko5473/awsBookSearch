package kwang.ho.service.board;

import kwang.ho.dto.board.CommentDto;

import java.util.List;

public interface CommentService {

    public List<CommentDto> commentListService(int bid) throws Exception;

    public int commentInsertService(CommentDto commentDto) throws Exception;

    public int commentUpdateService(CommentDto commentDto) throws Exception;

    public int commentDeleteService(int cid) throws Exception;
}
