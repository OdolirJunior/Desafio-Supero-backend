package com.odolirprojetosupero.repository;


import com.odolirprojetosupero.model.GroupTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupTodoRepository extends JpaRepository<GroupTodo, Long> {

}