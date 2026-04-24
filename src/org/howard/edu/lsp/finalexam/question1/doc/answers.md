Part 1:

Shared Resource #1:
The nextId variable, which is used to generate unique request IDs.

Shared Resource #2:
The requests list, which stores all student service requests.

Concurrency Problem:
Race condition.

Why addRequest() is unsafe:
The addRequest() method is unsafe because it accesses and modifies shared resources (nextId and requests) without synchronization. These operations are not atomic, so multiple threads may interleave execution. This can cause duplicate IDs or inconsistent updates to the list. For example, two threads could read the same nextId value before either increments it, resulting in duplicate request IDs.


Part 2:

Fix A: Explanation
Not correct. Synchronizing only getNextId() ensures that ID generation is thread-safe, but it does not protect the shared requests list. Multiple threads could still modify the list concurrently, leading to race conditions.

Fix B: Explanation
Correct. Synchronizing the entire addRequest() method ensures that both ID generation and adding to the list occur atomically. This prevents race conditions and guarantees thread safety.

Fix C: Explanation
Not correct. Synchronizing getRequests() only protects read access to the list. It does not prevent concurrent modifications during addRequest(), so race conditions can still occur.


Part 3:

Answer + Explanation
No, getNextId() should not be public. According to Riel’s heuristics, internal implementation details should be hidden unless necessary. Exposing this method allows external code to depend on or misuse the ID generation logic, which violates encapsulation.


Part 4:

Description:
An alternative approach is to use a ReentrantLock instead of the synchronized keyword. This provides explicit control over locking and ensures that only one thread can execute the critical section at a time, preventing race conditions.

Code Snippet:
private Lock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}