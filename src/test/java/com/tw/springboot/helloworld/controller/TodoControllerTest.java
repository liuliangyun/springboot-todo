package com.tw.springboot.helloworld.controller;

import com.tw.springboot.helloworld.entity.CreateRequestTodo;
import com.tw.springboot.helloworld.entity.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoControllerTest {

    @Autowired
    private TodoController todoController;

    @Before
    public void setUp() throws Exception {
        List<Todo> todos = new ArrayList<>();
        Todo.IdNum = 1;
        todos.add(new Todo("text1",false));
        todos.add(new Todo("text2",true));
        todos.add(new Todo("text3",false));
        todoController.mokaData(todos);
    }

    @Test
    public void test_add_todo() {
        final CreateRequestTodo createRequestTodo = new CreateRequestTodo();
        createRequestTodo.setText("spring boot");
        Todo todo = todoController.addTodo(createRequestTodo);

        assertThat(todo.getId()).isEqualTo(4);
        assertThat(todo.getText()).isEqualTo("spring boot");
        assertThat(todo.isCompleted()).isEqualTo(false);
        assertThat(todoController.getTodos().size()).isEqualTo(4);
    }

    @Test
    public void test_list_all_todo(){
        final List<Todo> list = todoController.listAllTodo();

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0).getId()).isEqualTo(1);
        assertThat(list.get(0).getText()).isEqualTo("text1");
        assertThat(list.get(0).isCompleted()).isEqualTo(false);
    }

    @Test
    public void test_delete_todo(){
        final int id = 1;
        Todo todo = todoController.deleteTodo(id);

        assertThat(todo.getId()).isEqualTo(1);
        assertThat(todo.getText()).isEqualTo("text1");
        assertThat(todo.isCompleted()).isEqualTo(false);
        assertThat(todoController.getTodos().size()).isEqualTo(2);
    }

    @Test
    public void test_modify_todo(){
        final int id = 1;
        final String text = "spring boot";
        Todo todo = todoController.modifyTodo(id,text);

        assertThat(todo.getId()).isEqualTo(1);
        assertThat(todo.getText()).isEqualTo("spring boot");
        assertThat(todo.isCompleted()).isEqualTo(false);
    }

    @Test
    public void test_filter_todo_by_status_completed(){
        final boolean status = true;
        List<Todo> filterList = todoController.filterTodoByStatus(status);

        assertThat(filterList.size()).isEqualTo(1);
        assertThat(filterList.get(0).getId()).isEqualTo(2);
        assertThat(filterList.get(0).getText()).isEqualTo("text2");
        assertThat(filterList.get(0).isCompleted()).isEqualTo(status);
    }

    @Test
    public void test_filter_todo_by_status_unCompleted(){
        final boolean status = false;
        List<Todo> filterList = todoController.filterTodoByStatus(status);

        assertThat(filterList.size()).isEqualTo(2);
        assertThat(filterList.get(0).getId()).isEqualTo(1);
        assertThat(filterList.get(0).getText()).isEqualTo("text1");
        assertThat(filterList.get(0).isCompleted()).isEqualTo(status);
    }
}