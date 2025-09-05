# bss-forum-service
bss-forum-service

CREATE TABLE Categories (
    category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

ALTER TABLE Posts ADD COLUMN category_id BIGINT NOT NULL;
ALTER TABLE Posts ADD CONSTRAINT FK_Post_Category FOREIGN KEY (category_id) REFERENCES Categories(category_id);

ALTER TABLE Posts 
ADD COLUMN status VARCHAR(50) DEFAULT 'Open';

INSERT INTO Categories (category_id,name) VALUES 
(100,'Microsoft'),
(101,'HP'),
(102,'ITES'),
(103,'Guardia');





INSERT INTO Users (user_id, username, password_hash, full_name, email) VALUES
(1, 'Chris', 'admin', 'Admin User', 'admin@example.com'),
(2, 'Noli', 'admin', 'John Doe', 'john@example.com'),
(3, 'Maruthi', 'admin', 'Jane Smith', 'jane@example.com'),
(4, 'Ei Leng', 'admin', 'Alice Wong', 'alice@example.com');

-- Insert posts, with category_id
INSERT INTO Posts (post_id, user_id, category_id, title, content, status) VALUES
(1, 2, 100, 'How to fix NullPointerException in Java?',
    'I am getting a NullPointerException in my Spring Boot application. How can I debug and fix it?','Open'),
(2, 3, 101, 'Best way to optimize SQL queries?',
    'My database queries are slow. What are some common SQL optimization techniques?','Open'),
(3, 4, 100, 'Windows 10 update stuck at 75%',
    'My Windows 10 update freezes at 75%. How can I fix this?','Open'),
(4, 2, 102, 'HP laptop overheating frequently',
    'My HP laptop heats up quickly and sometimes shuts down. What are the best monitoring tools or fixes?','Open');
	
	-- Insert posts (post_id, user_id, category_id, title, content)

INSERT INTO Posts (post_id, user_id, category_id, title, content,status) VALUES
(5, 2, 100, 'How to fix NullPointerException in Java?',
    'I am getting a NullPointerException in my Spring Boot application. How can I debug and fix it?','Open'),
(6, 3, 101, 'Best way to optimize SQL queries?',
    'My database queries are slow. What are some common SQL optimization techniques?','Open'),
(7, 4, 103, 'Windows 10 update stuck at 75%',
    'My Windows 10 update freezes at 75%. How can I fix this?','Open'),
(8, 2, 102, 'HP laptop overheating frequently',
    'My HP laptop heats up quickly and sometimes shuts down. What are the best monitoring tools or fixes?','Open'),
(9, 2, 100, 'Understanding Spring Boot @Transactional annotation',
    'Can someone explain how @Transactional works in Spring Boot and common pitfalls?','Open'),
(10, 3, 101, 'Indexing strategies for large tables',
    'How do I decide which columns to index in large transactional tables?','Open'),
(11, 2, 103, 'Troubleshooting Wi-Fi connection drops',
    'My laptop keeps dropping Wi-Fi connection randomly. What diagnostics can I perform?','Open'),
(12, 3, 102, 'Installing Python packages in virtual env',
    'How can I properly install Python packages inside a virtual environment?','Open'),
(13, 3, 100, 'Memory leak detection in Java applications',
    'What tools or methods are recommended to find memory leaks in production Java apps?','Open'),
(14, 2, 103, 'Linux server performance tuning tips',
    'My Linux server is slow under load. What kernel parameters should I tune?','Open'),
(15, 3, 102, 'Angular vs React - which is better for new projects?',
    'I want to start a new front-end project. Should I pick Angular or React, and why?','Open'),
(16, 2, 101, 'Explain database normalization with examples',
    'Can someone explain the different normal forms in database design with practical examples?','Open'),
(17, 4, 101, 'How to configure Nginx as a reverse proxy for Spring Boot',
    'Looking for practical steps to set up Nginx as a reverse proxy for a Spring Boot REST API.','Open'),
(18, 4, 101, 'Troubleshooting slow Docker container startup',
    'My Docker containers are taking too long to start, any common causes?','Open'),
(19, 4, 100, 'How to implement pagination in Spring Data JPA?',
    'What is the best way to implement efficient pagination for large datasets in Spring Data JPA?','Open'),
(20, 3, 101, 'Best practices for Git branching and merging',
    'What is a reliable Git branching strategy for a small development team?','Open'),
(21, 4, 102, 'Setting up continuous integration using Jenkins',
    'I want to set up a CI pipeline with Jenkins for my Maven project. What are the steps?','Open'),
(22, 3, 102, 'REST API versioning best practices',
    'What is the recommended way to version REST APIs in a backward compatible way?','Open'),
(23, 2, 100, 'Handling concurrency in Spring Boot',
    'How to properly manage concurrent requests and transactions in Spring Boot apps?','Open'),
(24, 4, 101, 'Explain ACID properties in databases',
    'I want a detailed explanation of ACID properties and why they matter.','Open'),
(25, 2, 103, 'Configuring SSL on Apache Tomcat',
    'How do I enable HTTPS/SSL support on my embedded Tomcat server?','Open'),
(26, 4, 103, 'Migrating from MySQL to PostgreSQL',
    'What are the best practices and common pitfalls in migrating databases from MySQL to PostgreSQL?','Open'),
(27, 2, 100, 'Improving React app performance',
    'How can I optimize rendering and reduce load times in my React application?','Open'),
(28, 2, 103, 'Fixing Blue Screen of Death in Windows',
    'Getting BSOD occasionally, what logs should I check and how to troubleshoot?','Open'),
(29, 2, 101, 'Explain difference between UNION and UNION ALL',
    'What is the difference between UNION and UNION ALL in SQL and when to use each?','Open'),
(30, 2, 101, 'Building a RESTful API with Flask',
    'Can someone provide a simple example of','Open');

	
	INSERT INTO Comments (comment_id, post_id, user_id, content) VALUES
(1, 1, 3, 'Make sure your object is initialized before calling methods on it. The stack trace usually pinpoints the exact line.'),
(2, 1, 4, 'Using Optional or checking for null explicitly can help avoid NullPointerException.'),
(3, 2, 2, 'Consider indexing your tables properly and avoid SELECT *.'),
(4, 3, 1, 'Try booting into safe mode and running Windows Update troubleshooter. Also, check free disk space.'),
(5, 4, 3, 'Make sure vents are clean and try using a cooling pad. Update BIOS if needed.');

-- Find the max comment_id
SELECT MAX(comment_id) FROM comments;

-- Suppose max comment_id is 5, reset the auto_increment to 6
ALTER TABLE comments ALTER COLUMN comment_id RESTART WITH 6;

-- Find the max comment_id
SELECT MAX(post_id) FROM posts;

-- Suppose max comment_id is 5, reset the auto_increment to 6
ALTER TABLE posts ALTER COLUMN post_id RESTART WITH 31;

INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (1, 5, NULL );
  INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (2, 7, NULL );
  INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (3, 8, NULL );
  INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (4, 1, NULL );
  INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (1, 1, NULL );
  INSERT INTO Likes (user_id, post_id, comment_id) VALUES 
  (2, 1, NULL );

