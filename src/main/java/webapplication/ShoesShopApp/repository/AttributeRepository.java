package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
