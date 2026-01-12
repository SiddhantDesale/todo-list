package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoDto {

    @NotBlank(message = "Todo title cannot be empty")
    private String title;

    private boolean completed;

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
