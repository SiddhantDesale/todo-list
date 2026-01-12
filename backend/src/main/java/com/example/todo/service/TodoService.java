package com.example.todo.service;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;

import java.util.List;

public interface TodoService {

    Todo createTodo(TodoDto dto);
    List<Todo> getAllTodos();
    Todo updateTodo(Long id, TodoDto dto);
    void deleteTodo(Long id);
}
