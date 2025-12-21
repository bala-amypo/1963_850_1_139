package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // This tells Spring: "I am the bean you are looking for!"
public class TransferRuleServiceImpl implements TransferRuleService {

    @Autowired
    private TransferRuleRepository ruleRepository;

    @Override
    public TransferRule saveRule(TransferRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<TransferRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}