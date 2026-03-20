# CRC Cards Collaboration Explanation

TaskManager

Responsibilities: Add tasks, prevent duplicates, find tasks by ID, filter tasks by status

Collaborators: Task


Task

Responsibilities: Store task data, update status, provide task details

Collaborators: None

# Redesigned Order Processing System – CRC Cards

**Class: Order**  
Responsibilities: Store customer name, email, item, and price. Provide access to order data.  
Collaborators: DiscountStrategy, ReceiptPrinter, OrderRepository, EmailNotifier

**Class: DiscountStrategy**  
Responsibilities: Apply discounts based on order or customer type.  
Collaborators: Order

**Class: ReceiptPrinter**  
Responsibilities: Generate and print a receipt for a given order.  
Collaborators: Order

**Class: OrderRepository**  
Responsibilities: Save order information to a persistent storage (file, database).  
Collaborators: Order

**Class: EmailNotifier**  
Responsibilities: Send confirmation email to customer.  
Collaborators: Order