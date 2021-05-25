package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Size;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    Boolean existsSizeByValue(String value);

}
