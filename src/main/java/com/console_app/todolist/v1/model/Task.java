package com.console_app.todolist.v1.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *  Above are Lombok Commands to generate the needed constructors for the Task Class
 *   - Data: Generates getters for all fields, setters for all non-final fields,
 *   a toString method, equals and hashCode implementations, and a constructor
 *   that takes all fields as parameters.
 *   - NoArgsConstructor: Generates a no-argument constructor.
 *   - AllArgsConstructor: Generates a constructor with one parameter for each
 *   field in the class.
 */
public class Task {
    private String title;
    private String description;
    private boolean isCompleted;
}


