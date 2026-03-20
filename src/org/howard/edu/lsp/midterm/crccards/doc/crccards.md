# CRC Cards Collaboration Explanation

## TaskManager

TaskManager collaborates with Task because it manages a collection of Task objects. Its responsibilities—adding tasks, preventing duplicates, finding tasks by ID, and filtering by status—require it to interact with Task objects to access their data and behavior.

## Task

Task does not collaborate with TaskManager because it is self-contained: it only stores its own data (task ID, description, status) and provides methods to update status or return details, so it does not need to know about TaskManager.