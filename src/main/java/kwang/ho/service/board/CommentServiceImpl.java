package kwang.ho.service.board;

import kwang.ho.dto.board.CommentDto;
import kwang.ho.mapper.board.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDto> commentListService(int bid) throws Exception {

        return commentMapper.commentList(bid);
    }

    public int commentInsertService(CommentDto commentDto) throws Exception{

        return commentMapper.commentInsert(commentDto);
    }

    public int commentUpdateService(CommentDto commentDto) throws Exception{

        return commentMapper.commentUpdate(commentDto);
    }

    public int commentDeleteService(int cid) throws Exception {

        return  commentMapper.commentDelete(cid);
    }
}
