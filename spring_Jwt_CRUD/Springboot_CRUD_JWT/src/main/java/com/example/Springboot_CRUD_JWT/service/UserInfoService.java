package com.example.Springboot_CRUD_JWT.service;

import com.example.Springboot_CRUD_JWT.entity.UserInfo;
import com.example.Springboot_CRUD_JWT.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public void updateUser(UserInfo updatedUser) {
        Optional<UserInfo> existingUserOptional = repository.findById(updatedUser.getId());

        existingUserOptional.ifPresent(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(encoder.encode(updatedUser.getPassword()));
            existingUser.setRoles(updatedUser.getRoles());
            repository.save(existingUser);
        });
    }

    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    public UserInfo getUser(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<UserInfo> getAllUsers() {
        return repository.findAll();
    }

}
