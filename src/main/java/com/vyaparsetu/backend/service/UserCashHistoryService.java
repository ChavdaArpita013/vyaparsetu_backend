package com.vyaparsetu.backend.service;

import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.entities.UserCashHistory;
import com.vyaparsetu.backend.repository.UserCashHistoryRepository;
import com.vyaparsetu.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserCashHistoryService {
    @Autowired
    private UserCashHistoryRepository userCashHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean addFunds(UserCashHistory userCashHistory , Long id){
        Optional<User> byId = userRepository.findById(id);
        //update if user available
        if(byId.isPresent()){
            User user = byId.get();
            double currentFunds = user.getTotalAvailableFunds() != null ? user.getTotalAvailableFunds() : 0.0;
            double addedFunds = userCashHistory.getAmount() + currentFunds;
            updateFunds(user , userCashHistory , addedFunds , "CREDIT");

            return true;
        }
        return false;
    }

    public boolean withdrawFunds(UserCashHistory userCashHistory , Long id){
        try {
            Optional<User> byId = userRepository.findById(id);
            //update if user available
            if(byId.isPresent()){
                User user = byId.get();
                double currentFunds = user.getTotalAvailableFunds() != null ? user.getTotalAvailableFunds() : 0.0;
                //if sufficient funds are available
                if(currentFunds >= userCashHistory.getAmount()){
                    double withdrawnFunds = currentFunds - userCashHistory.getAmount();
                    updateFunds(user , userCashHistory , withdrawnFunds , "DEBIT");
                    return true;
                }else{
                    log.info("Insufficient funds for user : {}", user.getUserName());
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            log.info("Error Withdrawing funds" , e);
            throw new RuntimeException(e);
        }
    }
    //update funds in both (user and cash history)
    public void updateFunds(User user , UserCashHistory userCashHistory ,double newFunds , String type){
        userCashHistory.setTime(LocalDateTime.now());
        userCashHistory.setType(type);
        userCashHistory.setUser(user);
        user.setTotalAvailableFunds(newFunds);
        user.getUserCashHistories().add(userCashHistory);
        userCashHistoryRepository.save(userCashHistory);
        userRepository.save(user);
    }
}
