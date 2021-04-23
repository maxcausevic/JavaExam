package com.mcausevic.javaExam.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mcausevic.javaExam.models.Task;
import com.mcausevic.javaExam.models.User;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long>{
	List<Task>findAll();
	 void deleteById(Long id);
	 Task save(Task x);
	 Optional<Task> findById(Long id);

}
