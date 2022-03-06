package com.sify.tam.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sify.tam.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByCustomerName(String firstName);
    public List<Customer> findByCreatedBy(String lastName);

}