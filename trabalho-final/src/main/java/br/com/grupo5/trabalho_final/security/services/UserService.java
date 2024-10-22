package br.com.grupo5.trabalho_final.security.services;

import br.com.grupo5.trabalho_final.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.UserRequestDTO;
// import br.com.grupo5.trabalho_final.security.dto.UserRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.UserResponseDTO;
import br.com.grupo5.trabalho_final.security.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public UserResponseDTO findUserById(Integer id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
    return new UserResponseDTO(user.getUsername(), user.getEmail(), user.getRoles());
  }

  public String deleteUserById(Integer id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User Not Found with id: " + id);
    }

    try {
      userRepository.deleteById(id);
      return "User deleted successfully!";
    } catch (Exception e) {
      throw new RuntimeException("Could not delete user with id: " + id);
    }
  }

  public String updateUserById(Integer id, UserRequestDTO user) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User Not Found with id: " + id);
    }
    try {
      User updateUser = userRepository.findById(id).get();
      if (user.getUsername() != null) {
        updateUser.setUsername(user.getUsername());
      }
      if (user.getEmail() != null) {
        updateUser.setEmail(user.getEmail());
      }
      userRepository.save(updateUser);
      return "User updated successfully!";
    } catch (Exception e) {
      throw new RuntimeException("Could not update user with id: " + id);
    }
  }

}
