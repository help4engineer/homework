package com.example.todo.controllers;

import com.example.todo.dao.TodoListDAO;
import com.example.todo.models.TodoList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@AllArgsConstructor
public class TodoListController {
    private TodoListDAO todoListDAO;

    @GetMapping("/todolists")
    public List <TodoList> getTodoLists() {
        return todoListDAO.findAll();
    }

    @PostMapping("/todolist/save")
    public void saveTodolist(@RequestBody String title) {
        todoListDAO.save(new TodoList(title));
    }

    @PutMapping("/todolist/{id}/update")
    public void updateTodoList(@PathVariable int id, @RequestBody String title) {
        TodoList one = todoListDAO.getOne(id);
        one.setTitle(title);
        one.setUpdatedAt(LocalDateTime.now());
//        one.setTodos(todoList.getTodos());
        todoListDAO.save(one);
    }

    @DeleteMapping("/todolist/{id}/delete")
    public void deleteTodoList(@PathVariable int id) {
        todoListDAO.deleteById(id);
    }

}
