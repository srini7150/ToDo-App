package com.example.ToDo.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDo.App.model.*;

@Repository
public interface iToDoRepo extends JpaRepository<ToDo, Long> {

}
