# Task Manager Spring Boot Project

## Overview
The Task Manager Spring Boot project is a simple application for managing tasks. It provides endpoints for adding new tasks, getting tasks, updating tasks, and deleting tasks. Additionally, this project includes integration testing to ensure the functionality works as expected.

## Features
- Create a new task.
- Retrieve task details.
- Update task details.
- Delete a task.
- Integration testing to verify the functionality.

## Getting Started
1. **Clone the Repository**

   If you have Git installed, you can clone the repository with the following command:
   ```
   git clone https://github.com/Pravesh-M/TaskManager.git
   ```
   Alternatively, you can download the project as a ZIP file and extract it.

2. **Import Project**
   
   Open your favorite IDE and import the project as a Maven project.

3. **Run the Application**

   Run the Spring Boot application using your IDE
   The application will start on http://localhost:8090.
   The actuator port is on http://localhost:8998.

## API Endpoints

- **Create a New Task**

  Endpoint: `POST /task`
  
  Request Body:
  ```json
  {
    "title": "Task Title",
    "description": "Task Description",
    "completed": "true/false"
  }
  ```
- **Get a Task by ID**

  Endpoint: `GET /task/{taskId}`

- **Get all Tasks**

  Endpoint: `GET /task/all`

- **Update a Task**

  Endpoint: `PUT /task/{isCompleted}`

- **Delete a Task**

  Endpoint: `DELETE /task/{taskId}`
