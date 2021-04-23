package com.mcausevic.javaExam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.mcausevic.javaExam.models.Task;
import com.mcausevic.javaExam.models.User;
import com.mcausevic.javaExam.repos.TaskRepo;

@Service
public class TaskService {
	private final TaskRepo taskRepo;
	public TaskService(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}
	 public Task findTaskById(Long id) {
	    	Optional<Task> t = taskRepo.findById(id);
	    	
	    	if(t.isPresent()) {
	            return t.get();
	    	} else {
	    	    return null;
	    	}
	    }
	 public List<Task>allTasks(){
			return taskRepo.findAll();
		}
		public Task createTasks(Task t) {
			return taskRepo.save(t);
		}
		 public void deleteTask(Long id) {
	    		Task task = findTaskById(id);
	    		taskRepo.delete(task);
	    		
	    	}
		 public Task updateTask(Long id, String name, String priority) {
				Task task = findTaskById(id);
				task.setName(name);
				task.setPriority(priority);
				
				taskRepo.save(task);
				return task;
		}

			public Task update(Task task) {
				return taskRepo.save(task);
			}

}
