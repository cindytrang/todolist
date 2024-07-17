package com.console_app.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.console_app.todolist.controller.TaskController;
import com.console_app.todolist.view.TaskView;
import com.console_app.todolist.model.Task;


import java.util.*;

@SpringBootApplication
public class TodolistApplication implements CommandLineRunner{
	@Autowired
	TaskController taskController;
	@Autowired
	TaskView taskView;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running To-Do List Application...");
		runApplication();
	}

	private void runApplication() {
		boolean running = true;
		while (running) {
			taskView.displayMenu();
			int choice = taskView.getIntInput();

			switch (choice) {
				case 1:
					addTask();
					break;
				case 2:
					viewTasks();
					break;
				case 3:
					updateTask();
					break;
				case 4:
					markTaskComplete();
					break;
				case 5:
					removeTask();
					break;
				case 6:
					running = false;
					System.out.println("Exiting application. Goodbye!");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void addTask() {
		System.out.println("Enter task title:");
		String title = taskView.getStringInput();
		System.out.println("Enter task description:");
		String description = taskView.getStringInput();
		taskController.addTask(title, description);
		System.out.println("Task added successfully.");
	}

	private void viewTasks() {
		taskView.displayTasks();
	}

	private void updateTask() {
		viewTasks();
		System.out.println("Enter the number of the task to update:");
		int index = taskView.getIntInput() - 1; // Subtract 1 because list is displayed 1-indexed

		Task task = taskController.getTask(index);
		if (task != null) {
			System.out.println("Enter new title (or press enter to keep current):");
			String title = taskView.getStringInput();
			if (!title.isEmpty()) {
				task.setTitle(title);
			}

			System.out.println("Enter new description (or press enter to keep current):");
			String description = taskView.getStringInput();
			if (!description.isEmpty()) {
				task.setDescription(description);
			}

			taskController.updateTask(index, task.getTitle(), task.getDescription());
			System.out.println("Task updated successfully.");
		} else {
			System.out.println("Invalid task number. No changes made.");
		}
	}

	private void markTaskComplete() {
		viewTasks();
		System.out.println("Enter the number of the task to mark as complete:");
		int index = taskView.getIntInput() - 1; // Subtract 1 because list is displayed 1-indexed
		taskController.markTaskComplete(index);
	}

	private void removeTask() {
		viewTasks();
		System.out.println("Enter the number of the task to remove:");
		int index = taskView.getIntInput() - 1; // Subtract 1 because list is displayed 1-indexed
		taskController.removeTask(index);
	}
}
