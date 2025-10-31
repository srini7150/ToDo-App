package com.example.ToDo.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDo.App.model.ToDo;
import com.example.ToDo.App.service.ToDoService;

@Controller	
public class controller {
	
     @Autowired
	private ToDoService service;
	
     @GetMapping ({"/", "viewToDoList"})
     public String viewAllToDoItems(Model model)
     {
    	 model.addAttribute("list", service.getAllToDoItems());
    	 return "ViewToDoList";
     }
     
     @PostMapping ("/updateToDoStatus/{id}")
     public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes)
     {
    	 if(service.updateStatus(id))
    	 {
    		 redirectAttributes.addFlashAttribute("message", "Update Success");
    		 return "redirect:/viewToDoList";
    	 }
    	// redirectAttributes.addFlashAttribute("message", "Update Failure");
		     redirectAttributes.addFlashAttribute("message","Update failure");
    	 return "redirect:/viewToDoList";
     }
     
     @GetMapping("/addToDoItem")
 	public String addToDoItem(Model model) {
 		model.addAttribute("todo", new ToDo());
 		
 		return "AddToDoItem";
 	}
     
     @PostMapping("/saveToDoItem")
     public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes)
     {
    	 if(service.saveOrUpdateToDoItem(todo))
    	 {
    	 redirectAttributes.addFlashAttribute("message", "Save success");
    	 return "redirect:/viewToDoList";
    	 }
    	 redirectAttributes.addFlashAttribute("message", "save failure");
    	 return "redirect:/addToDoItem";
     }
     
     @GetMapping("/editToDoItem")
     public String editToDoItem(@PathVariable Long id, Model model)
     {
    	 model.addAttribute("todo", service.getToDoItemsById(id));
    	 return "EditToDoItem";
     }
     
     @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes)
     {
    	 if(service.saveOrUpdateToDoItem(todo))
    	 {
    	 redirectAttributes.addFlashAttribute("message", "Edit success");
    	 return "redirect:/viewToDoList";
    	 }
    	 redirectAttributes.addFlashAttribute("message", "Edit failure");
    	 return "redirect:/editToDoItem"+ todo.getId();
     }
     
     @GetMapping("/deleteToDoItem/{id}")
     public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes)
     {
     if(service.deleteToDoItem(id))
	 {
	 redirectAttributes.addFlashAttribute("message", "Delete success");
	
	 }
	 redirectAttributes.addFlashAttribute("message", "Delete failure");
	 return "redirect:/viewToDoList";
}
}























