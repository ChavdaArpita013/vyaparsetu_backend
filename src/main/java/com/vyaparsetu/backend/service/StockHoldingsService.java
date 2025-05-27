package com.vyaparsetu.backend.service;

import com.vyaparsetu.backend.entities.Portfolios;
import com.vyaparsetu.backend.entities.StockHoldings;
import com.vyaparsetu.backend.entities.Transactions;
import com.vyaparsetu.backend.entities.User;
import com.vyaparsetu.backend.repository.PortfolioRepository;
import com.vyaparsetu.backend.repository.StockHoldingsRepository;
import com.vyaparsetu.backend.repository.TransactionsRepository;
import com.vyaparsetu.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class StockHoldingsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private StockHoldingsRepository stockHoldingsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    public boolean addStockHoldings(StockHoldings holdings , String userName){
        try {
            Portfolios portfolio = portfolioRepository.findUserByUserName(userName);
            User user = userRepository.findByUserName(userName);

            if(portfolio == null){
                portfolio = new Portfolios();
                portfolio.setUser(user);
                portfolio = portfolioRepository.save(portfolio);
            }
            holdings.setPortfolio(portfolio);
            portfolio.getHoldings().add(holdings);
                StockHoldings existingStock  = stockHoldingsRepository.findByStockIdAndPortfolio(holdings.getStockId() , portfolio);
                if(existingStock  == null){
                    stockHoldingsRepository.save(holdings);
                } else {
                    existingStock.setQty(existingStock .getQty() + holdings.getQty());
                    stockHoldingsRepository.save(existingStock );
                }
                Transactions transactions = new Transactions();
                transactions.setQty(holdings.getQty());
                transactions.setStockName(holdings.getStockName());
                transactions.setTime(LocalDateTime.now());
                transactions.setTransactionType("BUY");
                transactions.setPrice(holdings.getAvgPrice());
                transactions.setStockId(holdings.getStockId());
                transactions.setUser(user);
            Transactions save = transactionsRepository.save(transactions);
            if(save != null){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            log.info("Error while buying stocks",e);
            throw new RuntimeException(e);
        }
    }

    public boolean sellStockHoldings(StockHoldings holdings , String userName){
        try {
            Portfolios portfolios = portfolioRepository.findUserByUserName(userName);
            User user = userRepository.findByUserName(userName);
            portfolios.getHoldings().remove(holdings);

            StockHoldings existingHolding = stockHoldingsRepository.findByStockIdAndPortfolio(holdings.getStockId(), portfolios);

            if(existingHolding.getQty() > holdings.getQty()){
                existingHolding.setQty(existingHolding.getQty() - holdings.getQty());
                existingHolding.setAvgPrice((existingHolding.getAvgPrice() + holdings.getAvgPrice()) / existingHolding.getQty());
                stockHoldingsRepository.save(existingHolding);
            }else if(existingHolding.getQty() == holdings.getQty()){
                stockHoldingsRepository.delete(holdings);
            }

            Transactions transactions = new Transactions();
            transactions.setUser(user);
            transactions.setTransactionType("SELL");
            transactions.setStockName(holdings.getStockName());
            transactions.setTime(LocalDateTime.now());
            transactions.setQty(holdings.getQty());
            transactions.setPrice(holdings.getAvgPrice());
            transactions.setStockId(holdings.getStockId());
            Transactions save = transactionsRepository.save(transactions);
            return (save != null) ? true :false;


        } catch (Exception e) {
            log.info("Error while running sell transaction");
            throw new RuntimeException(e);
        }
    }
}
