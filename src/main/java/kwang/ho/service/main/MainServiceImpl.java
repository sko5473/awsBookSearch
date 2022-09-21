package kwang.ho.service.main;

import kwang.ho.dto.book.BestSellerDto;
import kwang.ho.mapper.main.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService{

    @Autowired
    private MainMapper mainMapper;

    @Override
    public BestSellerDto selectBestList() throws Exception {

        return mainMapper.selectBestList();
    }
}
