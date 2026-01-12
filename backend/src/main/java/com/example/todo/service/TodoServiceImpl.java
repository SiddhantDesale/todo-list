package com.example.todo.service;

import com.example.todo.dto.TodoDto;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo createTodo(TodoDto dto) {
        Todo todo = new Todo(dto.getTitle(), dto.isCompleted());
        return repository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Override
    public Todo updateTodo(Long id, TodoDto dto) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setTitle(dto.getTitle());
        todo.setCompleted(dto.isCompleted());
        return repository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        repository.delete(todo);
    }
}
