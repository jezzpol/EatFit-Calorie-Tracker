package com.Futkaradze.service.impl;

import com.Futkaradze.domain.Gender;
import com.Futkaradze.entity.User;
import com.Futkaradze.exception.UserException;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        if (!userRepository.existsById(id)) {
            throw new UserException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) throws UserException {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setWeight(updatedUser.getWeight());
        existingUser.setHeight(updatedUser.getHeight());
        existingUser.setTarget(updatedUser.getTarget());
        return userRepository.save(existingUser);
    }

    private void calculateDailyCalories(User user) {
        double BMR = calculateBMR(user);

        double activityLevel = getActivityLevel();

        int dailyCalories = (int) Math.round(BMR * activityLevel);

        user.setDailyCalories(dailyCalories);
    }

    private double calculateBMR(User user) {
        if (user.getGender() == Gender.MALE) {
            return 88.362
                    + (13.397 * user.getWeight())
                    + (4.799 * user.getHeight())
                    - (5.677 * user.getAge());
        } else {
            return 447.593
                    + (9.247 * user.getWeight())
                    + (3.098 * user.getHeight())
                    - (4.330 * user.getAge());
        }
    }

    private double getActivityLevel() { // Вынесено отдельно для расширяемости
        return 1.35; // Умеренная активность
    }
}
