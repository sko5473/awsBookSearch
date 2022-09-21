package kwang.ho.service.manage;

import kwang.ho.dto.book.BestSellerDto;
import kwang.ho.dto.book.BookDto;
import kwang.ho.dto.user.UserDto;

import java.util.List;

public interface ManageService {

    List<UserDto> selectUserList() throws Exception;

    List<UserDto> selectAdminUserList() throws Exception;

    void updateAdminAuth(List<String> user_Id) throws Exception;

    void deleteAdminAuth(List<String> user_Id) throws Exception;

    void saveBestList(List<String> bid) throws Exception;

    List<BookDto> searchBest(String bestKeyword) throws Exception;

}
