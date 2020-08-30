package com.dovile.simplewebservice.repository;

import com.dovile.simplewebservice.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dovile Barkauskaite <barkauskaite.dovile@gmail.com>
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
