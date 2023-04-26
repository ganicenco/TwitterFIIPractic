package ro.itschool.mapper;

import org.springframework.stereotype.Component;
import ro.itschool.controller.modelDTO.UserDTO;
import ro.itschool.entity.User;

@Component
public class UserMapper {

    public UserDTO fromEntity(User user){
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getRoles());
    }
    public User toEntity(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getRoles());
    }
}
