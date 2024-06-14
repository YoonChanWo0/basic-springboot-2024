package com.cw98.spring02.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

import com.cw98.spring02.domain.Todo;
import com.cw98.spring02.service.TodoService;

import jakarta.annotation.Resource;


@Controller
public class TodoController {
    
    @Resource
    TodoService todoService;

    @GetMapping("/todos")
    public String getTodos(Model model) throws Exception {
        List<Todo> allTodos = todoService.getTodos();
        model.addAttribute("todoList", allTodos); // view에 model로 todoList를 전달
        return "todolist";
    }
    

    @GetMapping("/todo/{tno}")
    public Todo getTodo(@PathVariable(name="tno") int tno) throws Exception{
        return todoService.getTodo(tno);
    }
    

}
