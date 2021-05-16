package com.bank.api.repository;

import com.bank.api.entity.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends CrudRepository<Contractor, Long> {

}
