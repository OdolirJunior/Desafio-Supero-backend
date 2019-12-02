package com.odolirprojetosupero.repository;


import com.odolirprojetosupero.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.user = ?1")
    Optional<Object> findByName(String id);

    Object findAll(Sort sortByIdAsc);
}
