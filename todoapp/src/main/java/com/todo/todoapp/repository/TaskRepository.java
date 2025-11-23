package com.todo.todoapp.repository;

import com.todo.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository<EntityName,primaryColName>
public interface TaskRepository extends JpaRepository<Task,Long> {
    long countByCompleted(boolean b);

    List<Task> findByCompleted(boolean b);
}
