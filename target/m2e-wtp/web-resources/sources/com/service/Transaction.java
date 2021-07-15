package com.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Transactional (propagation = Propagation.REQUIRED)
public abstract class Transaction {

}
