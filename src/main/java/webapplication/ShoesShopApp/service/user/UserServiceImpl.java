package webapplication.ShoesShopApp.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.Role;
import webapplication.ShoesShopApp.model.dto.EditUserStatusDto;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.repository.RoleRepository;
import webapplication.ShoesShopApp.repository.UserRepository;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(@Valid UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")),registrationDto.isBlocked());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        if(user.getBlocked() == true){
            throw new UsernameNotFoundException("User is blocked!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles){
        return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    public List<User> listAll(){
        return  userRepository.findAll();
    }

    public void delete(long id){ userRepository.deleteById(id); }

    @Transactional
    public void changeUserStatus(long id, EditUserStatusDto editUserStatusDto){//shift f6
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setBlocked(editUserStatusDto.isBlocked());
            List<Role> roles = new LinkedList<>();
            Role role = this.roleRepository.findByName(editUserStatusDto.getRole());
            roles.add(role);
            user.setRoles(roles);
        }
        //przekazanie usera (email) z thymeleaf
        //zaaktualizowanie pola block
        //zapis

        //tak samo dla roli
    }
    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

}
