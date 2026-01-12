package com.example.todo.controller;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public Todo create(@Valid @RequestBody TodoDto dto) {
        return service.createTodo(dto);
    }

    @GetMapping
    public List<Todo> getAll() {
        return service.getAllTodos();
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @Valid @RequestBody TodoDto dto) {
        return service.updateTodo(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}
