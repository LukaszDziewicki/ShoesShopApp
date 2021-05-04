package webapplication.ShoesShopApp.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    private String city;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String street;
    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();

    public Address() {
    }
    public Address(String city, String houseNumber, String postalCode, String country, String street) {
        this.city = city;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
        this.street = street;
    }
    public Long getAddressId() {
        return addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
