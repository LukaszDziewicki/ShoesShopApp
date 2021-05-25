package webapplication.ShoesShopApp.service.size;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.repository.SizeRepository;

import java.util.List;

@Service
@Transactional
public class SizeServiceImpl {

    @Autowired
    private SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public void save(Size size){
        sizeRepository.save(size);
    }

    public List<Size> listAll(){
        return sizeRepository.findAll();
    }
    public boolean isEqualSize(String value){
        return sizeRepository.existsSizeByValue(value);
    }
}
