# 📚 Book Management System

A Spring Boot backend application to manage books and authors with clean architecture, pagination, sorting, and filtering.

---

## 🚀 Features
- CRUD operations for Books & Authors  
- Pagination & Sorting using Pageable  
- Search books by title  
- Filter books by author, date and genre 
- DTO pattern for clean API design  
- Global exception handling  

---

## 🛠️ Tech Stack
- Java, Spring Boot  
- Spring Data JPA (Hibernate)  
- MySQL  
- ModelMapper  
- Maven  

---

## 📌 API Overview
- `/authors` → Manage authors
- '/authors/search' → Search by name
- `/books` → Manage books  
- `/books/search/title` → Search by title  
- `/books/search/author` → Books by author
- `/books/search/genre` → Books by genre  
- `/books/search/date` → Filter by date  

---

## 💡 Key Concepts Used
- DTO vs Entity separation  
- JPA relationships (@OneToMany, @ManyToOne)  
- Pagination & sorting  
- REST API design  
- Debugging real-world issues  

---

## 👨‍💻 Author
Ankit Suthar
