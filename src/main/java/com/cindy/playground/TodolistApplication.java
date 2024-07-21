package com.cindy.playground;

import com.cindy.playground.example1.v1.controller.InputHandler;
import com.cindy.playground.example1.v1.controller.TaskController;
import com.cindy.playground.example1.v1.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.cindy.playground.example1.v1.view.TaskView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class responsible for managing a To-Do List application.
 * This class is responsible for interactions between the Task Controller, Task View,
 * and user input handling.
 */
@SpringBootApplication
public class TodolistApplication implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(TodolistApplication.class);
	@Autowired
    TaskController taskController;
	@Autowired
	TaskView taskView;
	@Autowired
    InputHandler inputHandler;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting  To-Do List application");
		runApplication();
		logger.info("To-Do List application finished");
	}

	/**
	 * Runs the main loop of the To-Do List application, displaying a menu to the user
	 * and handling their input to perform various tasks like adding, viewing, updating,
	 * completing or removing tasks, and exiting the application.
	 *
	 * Can be extended by more operations
	 */
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
					toggleTaskCompletion();
					break;
				case 5:
					removeTask();
					break;
				case 6:
					running = false;
					System.out.println("Exiting application. Done!");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void addTask() {
		String title = inputHandler.getInput("Enter task title:");
		String description = inputHandler.getInput("Enter task description:");
		taskController.addTask(title, description);
		System.out.println("Task added successfully.");
	}

	private void viewTasks() {
		taskView.displayTasks();
	}

	private void updateTask() {
		viewTasks();
		int index = inputHandler.getTaskIndex("Enter the number of the task to update:");
		if (index == -1) return;

		Task task = taskController.getTask(index);
		String newTitle = inputHandler.getUpdatedField("Enter new title (or press enter to keep current):", task.getTitle());
		String newDescription = inputHandler.getUpdatedField("Enter new description (or press enter to keep current):", task.getDescription());

		taskController.updateTask(index, newTitle, newDescription);
		System.out.println("Task updated successfully.");
	}

	private void toggleTaskCompletion() {
		viewTasks();
		int index = inputHandler.getTaskIndex("Enter the number of the task to mark as complete:");
		if (index != -1) {
			Task task = taskController.getTask(index);
			task.setCompleted(!task.isCompleted());
		}

	}

	private void removeTask() {
		viewTasks();
		int index = inputHandler.getTaskIndex("Enter the number of the task to remove:");
		if (index != -1) {
			taskController.removeTask(index);
			System.out.println("Task removed successfully.");
		}
	}
}
