package com.example.demo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sd_ownersRepository extends JpaRepository<sd_owners, BigInteger> {
    List<sd_owners> findByFirstname(String firstname);
    List<sd_owners> findByEmail(String Email);
}
