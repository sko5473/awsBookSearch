package kwang.ho.mapper.main;

import kwang.ho.dto.book.BestSellerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

    BestSellerDto selectBestList() throws Exception;
}
