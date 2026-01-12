import React, { useEffect, useState } from "react";
import {
  getTodos,
  createTodo,
  updateTodo,
  deleteTodo,
} from "../services/TodoService";
import "../App.css";

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    loadTodos();
  }, []);

  const loadTodos = () => {
    getTodos().then((res) => setTodos(res.data));
  };

  const addTodo = () => {
    if (!title.trim()) return;

    createTodo({ title, completed: false }).then(() => {
      setTitle("");
      loadTodos();
    });
  };

  const toggleCompleted = (todo) => {
    updateTodo(todo.id, {
      title: todo.title,
      completed: !todo.completed,
    }).then(loadTodos);
  };

  const updateTitle = (todo) => {
    updateTodo(todo.id, {
      title,
      completed: todo.completed,
    }).then(() => {
      setEditingId(null);
      setTitle("");
      loadTodos();
    });
  };

  return (
    <div className="container">
      <h2>✍️ Todo List</h2>

      <div className="input-box">
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Enter todo..."
        />
        <button
          onClick={
            editingId
              ? () => updateTitle(todos.find((t) => t.id === editingId))
              : addTodo
          }
        >
          {editingId ? "Update" : "Add"}
        </button>
      </div>

      <ul>
        {todos.map((todo) => (
          <li key={todo.id} className={todo.completed ? "done" : ""}>
            <input
              type="checkbox"
              checked={todo.completed}
              onChange={() => toggleCompleted(todo)}
            />

            <span
              onDoubleClick={() => {
                setEditingId(todo.id);
                setTitle(todo.title);
              }}
            >
              {todo.title}
            </span>

            <button
              className="delete"
              onClick={() => deleteTodo(todo.id).then(loadTodos)}
            >
              ❌
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList;
