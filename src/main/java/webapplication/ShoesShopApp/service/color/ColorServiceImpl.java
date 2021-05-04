package webapplication.ShoesShopApp.service.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.repository.ColorRepository;

import java.util.List;

@Service
@Transactional
public class ColorServiceImpl {

    @Autowired
    private ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public void save(Color color){
        colorRepository.save(color);
    }

    public List<Color> listAll(){
        return colorRepository.findAll();
    }
}
