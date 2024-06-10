package com.example.dacn.services;

import com.example.dacn.dtos.RegisterDTO;
import com.example.dacn.dtos.UserDTO;
import com.example.dacn.entities.Category;
import com.example.dacn.entities.User;
import com.example.dacn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    private UserDetailsManager userDetailsManager;

    @Autowired
    void setLazyField(@Lazy UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserEntityByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
    }

    public String getLoginUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Long getLoginUserId() {
        String username = getLoginUsername();
        User user = getUserEntityByUsername(username);
        return user.getId();
    }

    public UserDTO getUserByUsername(String username) {
        User user = getUserEntityByUsername(username);
        return convertToUserDTO(user);
    }

    public void createUser(RegisterDTO registerDTO) {
        boolean isUserExists = userDetailsManager.userExists(registerDTO.getUsername());
        if (isUserExists) {
            LOGGER.error("There is already exists username {} in our system", registerDTO.getUsername());
            return;
        }

        userDetailsManager.createUser(convertToUserDetails(registerDTO));
        List<Category> defaultCategories = categoryService.getDefaultCategories();
        this.addDefaultCategoriesToUser(registerDTO.getUsername(), defaultCategories);
    }

    private void addDefaultCategoriesToUser(String username, List<Category> categories) {
        User user = this.getUserEntityByUsername(username);
        user.setCategoryList(categories);
        userRepository.save(user);
    }

    private User getUserEntityByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .categoryList(categoryService.convertToCategoryDTOs(user.getCategoryList()))
                .build();
    }

    private UserDetails convertToUserDetails(RegisterDTO registerDTO) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(registerDTO.getUsername())
                .password(String.format("{noop}%s", registerDTO.getPassword()))
                .roles("USER")
                .build();
    }
}
