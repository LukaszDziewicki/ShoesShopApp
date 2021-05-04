package webapplication.ShoesShopApp.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import webapplication.ShoesShopApp.model.Role;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.dto.EditUserStatusDto;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.repository.RoleRepository;
import webapplication.ShoesShopApp.repository.UserEmailRepository;
import webapplication.ShoesShopApp.repository.UserRepository;
import webapplication.ShoesShopApp.controller.UserController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetails {

    private Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserEmailRepository userEmailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User user = new User();


    @Override
    public User save(@Valid UserRegistrationDto registrationDto) {
        List<Role> roles = roleRepository.findAll();
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Set.of(roles.get(1)), registrationDto.isBlocked());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        if (user.getBlocked() == true) {
            throw new UsernameNotFoundException("User is blocked!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public void changeUserStatus(long id, EditUserStatusDto editUserStatusDto) {//shift f6
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlocked(editUserStatusDto.isBlocked());

            Role role = roleRepository.findById(id);
            user.setRoles(editUserStatusDto.getRoles());
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void saveUserData(User user) {
        userRepository.save(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Optional<User> getByEmail(String email) {

        return userEmailRepository.findByEmail(email);
    }

    public void changeUserPassword(@RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword,
                                   Principal principal) {

        String userName = principal.getName();
        Optional<User> loggedUser = getByEmail(userName);
        if (loggedUser.isPresent()) {
            User currentUser = loggedUser.get();

            if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {

                currentUser.setPassword(this.passwordEncoder.encode(newPassword));
                saveUserData(currentUser);
                logger.info("Successful! Password changed");

            }
            
            else {

                    logger.info("Incorrect old password");
            }
        }
    }
}

