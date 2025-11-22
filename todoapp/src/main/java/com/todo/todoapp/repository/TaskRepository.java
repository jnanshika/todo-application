package com.todo.todoapp.repository;

import com.todo.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<EntityName,primaryColName>
public interface TaskRepository extends JpaRepository<Task,Long> {
}
