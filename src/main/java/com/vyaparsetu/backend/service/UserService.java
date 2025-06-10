package com.vyaparsetu.backend.service;

import com.vyaparsetu.backend.entities.BankDetails;
import com.vyaparsetu.backend.entities.Portfolios;
import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.repository.PortfolioRepository;
import com.vyaparsetu.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserService {
    private static final int USER_ID_NUMBER_LIMIT = 999;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private EmailService emailService;
    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public String generateUserId() {
        List<String> existingIds = userRepository.findAllUserIds(); // custom projection
        Set<String> existingSet = new HashSet<>(existingIds);

        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                for (char c3 = 'A'; c3 <= 'Z'; c3++) {
                    String prefix = "" + c1 + c2 + c3;
                    for (int num = 1; num <= USER_ID_NUMBER_LIMIT; num++) {
                        String userId = prefix + String.format("%03d", num);
                        if (!existingSet.contains(userId)) {
                            return userId;
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Exhausted all possible user IDs");
    }

    public boolean createDeMateAccount(User user){
        try {
            String userId = generateUserId();
            user.setUserId(userId);

            User save = userRepository.save(user);
            Portfolios portfolios = new Portfolios();
            portfolios.setUser(save);
            portfolioRepository.save(portfolios);

            if(save != null){
               emailService.sendMail(user.getEmail() , user.getUserName());
               return true;
            }


        } catch (Exception e) {
            log.info("Error creating demate account " , e);
        }
        return false;
    }

    public boolean loginUser(String userName , String userId , String password){
        User user = userRepository.findByUserName(userName);
        return Objects.equals(user.getUserId(), userId) && Objects.equals(user.getPassword(), password);
    }
}
