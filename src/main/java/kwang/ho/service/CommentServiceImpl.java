package kwang.ho.service;

import kwang.ho.board.CommentDto;
import kwang.ho.mapper.CommentMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.Collections;
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
