package com.console_app.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.console_app.todolist.controller.TaskController;
import com.console_app.todolist.view.TaskView;
import java.util.*;

@SpringBootApplication
public class TodolistApplication {
	TaskController taskController = new TaskController();
	TaskView taskView = new TaskView();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);

	}

}
