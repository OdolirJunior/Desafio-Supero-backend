package com.odolirprojetosupero.repository;


import com.odolirprojetosupero.model.GroupTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupTodoRepository extends JpaRepository<GroupTodo, Long>{
    @Query("SELECT t FROM GroupTodo t WHERE t.userId = ?1")
    List<GroupTodo> findByUser(Long id);
}