package kwang.ho.service.book;

import kwang.ho.common.FileUtils;
import kwang.ho.dto.board.AttachDTO;
import kwang.ho.dto.board.PagingVO;
import kwang.ho.dto.book.BookAttachDto;
import kwang.ho.dto.book.BookDto;
import kwang.ho.mapper.board.AttachMapper;
import kwang.ho.mapper.book.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private AttachMapper attachMapper;

    // 도서게시글 목록
    @Override
    public List<BookDto> selectBookListWithPaging(PagingVO pagingVO) throws Exception {
        return bookMapper.selectBookListWithPaging(pagingVO);
    }

    // 도서게시글 상세보기
    @Override
    public BookAttachDto selectBestDetail(int bid) throws Exception {
        return bookMapper.selectBestDetail(bid);
    }

    @Override
    public BookDto selectBookDetail(int bid) throws Exception {
        return bookMapper.selectBookDetail(bid);
    }

    // 도서게시글 등록
    @Override
    public boolean insertBook(BookDto bookDto, MultipartFile[] files) throws Exception {

        int queryResult = 1;

        /*if (insertBook(bookDto) == false) {
            return false;
        }*/
        bookMapper.insertBook(bookDto);
        List<AttachDTO> fileList = fileUtils.uploadFiles(files, bookDto.getBid());
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

    // 도서게시글 삭제
    @Override
    public void bookDelete(int bid) throws Exception {
        bookMapper.bookDelete(bid);
    }

    // 도서게시글 수정페이지 호출
    @Override
    public BookDto selectOpenBookModify(int bid) throws Exception {
        return bookMapper.selectOpenBookModify(bid);
    }

    // 도서게시글 수정
    @Override
    public void bookModify(BookDto bookDto, MultipartFile[] files) throws Exception {

        bookMapper.bookModify(bookDto);

        if ("Y".equals(bookDto.getChangeYn())) {
            attachMapper.deleteAttach(bookDto.getBid());

            // fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 'N'으로 업데이트
            if (CollectionUtils.isEmpty(bookDto.getFileIdxs()) == false) {
                attachMapper.undeleteAttach(bookDto.getFileIdxs());
            }
        }

        List<AttachDTO> fileList = fileUtils.uploadFiles(files, bookDto.getBid());
        if (!CollectionUtils.isEmpty(fileList)) {
            attachMapper.modifyAttach(fileList);

            for(AttachDTO data: fileList){
                System.out.println("data = " + data);
            }
        }
    }

    // 도서게시글 갯수
    @Override
    public int selectBookTotalCount() throws Exception {
        return bookMapper.selectBookTotalCount();
    }

    @Override
    public List<AttachDTO> getAttachFileList(int boardIdx) {
        return attachMapper.selectAttachList(boardIdx);
    }
}
