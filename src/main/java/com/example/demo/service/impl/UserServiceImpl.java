package com.example.demo.service.impl;

import com.example.demo.config.KafkaProducerConfig;
import com.example.demo.config.KafkaTopicConfig;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final KafkaProducerConfig kafkaProducerConfig;

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public User update(User user) {
        try {
            kafkaProducerConfig.sendMessage("Updated the user with id : " + user.getId());
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try {
            userRepository.delete(user);
            kafkaProducerConfig.sendMessage("Deleted the user with id : " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
            kafkaProducerConfig.sendMessage("Deleted the user with id : " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public User findById(long id) {
        try {
            kafkaProducerConfig.sendMessage("Found the user with id : " + id);
            return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error saving user" + id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user" + e.getMessage());
        }
    }

    @Override
    public boolean login(String username, String password) {
        try {
            kafkaProducerConfig.sendMessage("Logging in the user ");
            return userRepository.login(username, password) != null;
        } catch (Exception e) {
            throw new RuntimeException("Error logging user" + e.getMessage());
        }
    }

    @Override
    public User register(User user) {
        try {
            kafkaProducerConfig.sendMessage("Registering the user ");
            return userRepository.save(User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .username(user.getUsername())
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error logging user" + e.getMessage());
        }
    }
}
