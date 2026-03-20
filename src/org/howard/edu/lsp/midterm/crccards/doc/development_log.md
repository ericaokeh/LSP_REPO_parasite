# Development Log

## External Resources Used

### AI Tool: Chat GBT

---

Student: I'm starting to design this task system, and I'm not sure how to organize everything. Should I just put all the logic in one class, or split it up?

AI Assistant: What different responsibilities do you think exist in your system?

Student: Well, each task has its own data like ID, description, and status. But there's also stuff like storing all tasks, adding them, and searching through them.

AI Assistant: That sounds like two different types of responsibilities. How might you separate them?

Student: Maybe one class for individual tasks and another for managing all of them. So like a Task class and a TaskManager class.

AI Assistant: That separation makes sense. What would each class be responsible for?

Student: Task would just hold its own data and update its status. TaskManager would store all tasks, add new ones, and handle searching or filtering.

Student: Now I'm trying to decide how to store the tasks inside TaskManager. My first thought was to use an ArrayList since it's simple.

AI Assistant: How would you find a specific task by ID using an ArrayList?

Student: I'd have to loop through the whole list and compare IDs one by one. That feels inefficient if there are a lot of tasks.

AI Assistant: Is there a structure that allows direct access using a key?

Student: Oh, maybe a Map. I could use the task ID as the key and the Task object as the value.

AI Assistant: What benefits would that give you?

Student: It would make finding tasks much faster, like constant time instead of looping. Also, I could prevent duplicates by checking if the key already exists before adding.

AI Assistant: So what structure would you choose?

Student: A HashMap, since it supports fast lookup and ensures unique task IDs.

Student: I'm also thinking about how to handle task status. Right now I'm just using Strings like "OPEN" or "IN_PROGRESS".

AI Assistant: Are there any risks with using raw Strings?

Student: Yeah, someone could type something invalid like "done" or misspell it, and it wouldn't be consistent.

AI Assistant: How could you handle that?

Student: I could validate the input in the setter method and only allow specific values like "OPEN", "IN_PROGRESS", and "COMPLETE".

AI Assistant: What should happen if someone enters an invalid status?

Student: At first I thought about just setting it to something like "UNKNOWN", but that might hide mistakes. It's probably better to throw an exception so the error is clear.

Student: I also realized I need to prevent duplicate task IDs. What's the best way to handle that?

AI Assistant: How does your current data structure help with that?

Student: Since I'm using a HashMap, I can check if the key already exists before adding a new task.

AI Assistant: And if it does exist?

Student: Then I should throw an IllegalArgumentException to stop the duplicate from being added.

AI Assistant: Why throw an exception instead of ignoring it?

Student: Because duplicates would break the system's logic, and it's better to fail early than silently allow bad data.

Student: For finding tasks, I'm returning null if the task isn't found. Is that okay?

AI Assistant: What would the caller need to do in that case?

Student: They'd have to check if the result is null before using it.

AI Assistant: Does that seem reasonable for your program?

Student: Yeah, I think it's simple enough for this case. I can just handle it in the Driver by checking and printing "Task not found."

Student: I think everything is coming together now. Let me make sure I understand the overall design.

AI Assistant: Go ahead.

Student: Task is responsible for its own data and behavior, like storing its ID, description, and status. TaskManager is responsible for managing a collection of tasks, including adding, searching, and filtering them. It uses a HashMap to store tasks efficiently and prevent duplicates.

AI Assistant: That sounds like a clear and well-structured design.

Student: Yeah, I feel like it follows good separation of concerns and makes the system easier to manage.
