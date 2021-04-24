package com.mcausevic.javaExam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.mcausevic.javaExam.models.Task;
import com.mcausevic.javaExam.models.User;
import com.mcausevic.javaExam.services.TaskService;
import com.mcausevic.javaExam.services.UserService;
import com.mcausevic.javaExam.validator.UserValidator;

@Controller
public class MainController {
	private final UserService userService;
	private final TaskService taskService;
	private final UserValidator userValidator;
	public MainController(UserService userService, UserValidator userValidator, TaskService taskService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.taskService = taskService;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,RedirectAttributes redirect){
		  userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "index.jsp";
		}else {
			User u = userService.registerUser(user);
			session.setAttribute("userId", u.getId());
			redirect.addFlashAttribute("success", "You have successfully registered!");

			return "redirect:/dashboard";
		}
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model Model, HttpSession session, RedirectAttributes redirect) {
		if (userService.authenticateUser(email, password)) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			redirect.addFlashAttribute("success", "You have successfully logged in!");
			return "redirect:/dashboard";
		}else {
			redirect.addFlashAttribute("error", "Invalid login Credentials!");
			return "redirect:/";
		}
	}
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, RedirectAttributes redirect) {
		Long userId = (Long)session.getAttribute("userId");
		if(userId == null) {
			redirect.addFlashAttribute("please", "Please register or login!");
			return "redirect:/";
		}
		model.addAttribute("user", userService.findUserById(userId));
		model.addAttribute("task", new Task());
		model.addAttribute("tasks", taskService.allTasks());
		
		return "dashboard.jsp";
	}
	@RequestMapping(value="/createTaskPage", method=RequestMethod.GET)
	public String taskPage(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("assignees", userService.allUsers());
		
		return "add.jsp";
	}
	
	@RequestMapping(value="/createTask",method=RequestMethod.POST)
	public String createTask(@Valid @ModelAttribute("task") Task  task, BindingResult result, @RequestParam("assignees") Long assigneeID, Model model, HttpSession session) {
		User user = userService.findUserById((Long)session.getAttribute("userId"));
		User assignee = userService.findUserById(assigneeID);
		if(result.hasErrors()) {
			model.addAttribute("assignees", userService.allUsers());
			return "add.jsp";
		}else
			task.setCreator(user);
			
			taskService.createTasks(task);
			
		return "redirect:/dashboard";
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		
		taskService.deleteTask(id);
		return "redirect:/dashboard";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	@RequestMapping("/taskEdit/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		Task task = taskService.findTaskById(id);
		model.addAttribute("assignees", userService.allUsers());
		model.addAttribute("task", task);
		return "edit.jsp";
	}
	@RequestMapping(value="/taskEditUpate/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("task") Task task, BindingResult result, @RequestParam("assignees") Long assigneeId, @PathVariable("id") Long id, HttpSession session, Model model) {
		User user = userService.findUserById((Long)session.getAttribute("userId"));
		User assignee = userService.findUserById(assigneeId);
        if (result.hasErrors()) {
        	model.addAttribute("assignees", userService.allUsers());
        	return "edit.jsp";
        } else {
        	
        	task.setCreator(user);
            taskService.update(task);
            return "redirect:/dashboard";
        }
	}
        @RequestMapping("/showTask/{id}")
		public String showTask(Model model, @PathVariable("id") Long id) {
			Task task = taskService.findTaskById(id);
			User assignee = userService.findUserById(id);
			
			model.addAttribute("task", task);
			model.addAttribute("assignees", userService.allUsers());
			//model.addAttribute("assignee", assignee);
			
			
			return "show.jsp";
	}
}


