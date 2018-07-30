package com.tw.springboot.helloworld.controller;

import com.tw.springboot.helloworld.entity.CreateRequestTodo;
import com.tw.springboot.helloworld.entity.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }


    public void mokaData(List<Todo> todos) {
        this.todos.clear();
        this.todos.addAll(todos);
    }

    @GetMapping("/addTodo")
    public Todo addTodo(CreateRequestTodo createRequestTodo) {
        String text = createRequestTodo.getText();
        if(text.length()>0){
            Todo todo = new Todo(text,false);
            todos.add(todo);
            return todo;
        }
        return null;
    }

    @GetMapping("/todos")
    public List<Todo> listAllTodo() {
        return todos;
    }

    @GetMapping("/deleteTodo")
    public Todo deleteTodo(int id) {
        final Optional<Todo> selectedTodo = todos.stream().filter(todo -> todo.getId() == id).findFirst();
        if(selectedTodo.isPresent()){
            todos.remove(selectedTodo.get());
            return selectedTodo.get();
        }
        return null;
    }

    @GetMapping("/modifyTodo")
    public Todo modifyTodo(int id, String text) {
        final Optional<Todo> selectedTodo = todos.stream().filter(todo -> todo.getId() == id).findFirst();
        if(selectedTodo.isPresent()){
            Todo todo = selectedTodo.get();
            todo.setText(text);
            return todo;
        }
        return null;
    }

    @GetMapping("/filterTodo")
    public List<Todo> filterTodoByStatus(boolean status) {
        final List<Todo> list = todos.stream().filter(todo -> todo.isCompleted() == status).collect(Collectors.toList());
        return list;
    }
}
