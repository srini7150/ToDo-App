package com.example.ToDo.App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDo.App.model.*;
import com.example.ToDo.App.repository.iToDoRepo;

@Service
public class ToDoService {

	@Autowired
	iToDoRepo repo;
	
	public List<ToDo> getAllToDoItems()
	{
		ArrayList<ToDo> todoList = new ArrayList<>();
		repo.findAll().forEach(todo -> todoList.add(todo));
		return todoList;
	}
	
	public ToDo getToDoItemsById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id)
	{
		ToDo todo = getToDoItemsById(id);
		todo.setStatus("completed");
		return saveOrUpdateToDoItem(todo);
		
	}
	
	public boolean saveOrUpdateToDoItem(ToDo todo)
	{
		ToDo updatedObj = repo.save(todo);
		if(getToDoItemsById(updatedObj.getId()) != null)
		{
			return true;
		}
		return false;
	}
	
	
	public boolean deleteToDoItem(Long id)
	{
		repo.deleteById(id);
		
		if(getToDoItemsById(id) == null)
		{
			return true;
		}
		return false;
	}
	

}
