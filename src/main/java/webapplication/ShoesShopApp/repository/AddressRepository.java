package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
