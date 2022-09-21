package kwang.ho.service.main;

import kwang.ho.dto.book.BestSellerDto;

import java.util.List;

public interface MainService {

    BestSellerDto selectBestList() throws Exception;
}
