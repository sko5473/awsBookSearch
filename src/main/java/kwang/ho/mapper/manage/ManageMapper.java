package kwang.ho.mapper.manage;

import kwang.ho.dto.book.BestSellerDto;
import kwang.ho.dto.book.BookDto;
import kwang.ho.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface ManageMapper {

    // 전체유저목록 조회
    List<UserDto> selectUserList() throws Exception;

    List<UserDto> selectAdminUserList() throws Exception;

    void updateAdminAuth(List<String> user_Id) throws Exception;

    void deleteAdminAuth(List<String> user_Id) throws Exception;

    void saveBestList(List<String> bid) throws Exception;

    List<BookDto> searchBest(String bestKeyword) throws Exception;
}
