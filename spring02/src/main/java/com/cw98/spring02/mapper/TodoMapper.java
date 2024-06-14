package com.cw98.spring02.mapper;

import java.util.List;

import com.cw98.spring02.domain.Todo;


public interface TodoMapper {
    List<Todo> selectTodoAll() throws Exception;

    Todo selectTodoByTno(int tno) throws Exception;
    
}
