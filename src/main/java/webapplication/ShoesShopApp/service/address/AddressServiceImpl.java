package webapplication.ShoesShopApp.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.Address;
import webapplication.ShoesShopApp.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl {

    @Autowired
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }
}
