# Online Quiz Application

A Spring Boot-based RESTful API for creating, managing, and taking online quizzes. Supports quizzes, questions, options, and quiz-taking with scoring. Uses PostgreSQL as the database.

## Features

- Create, retrieve, and delete quizzes
- Add questions to quizzes
- Add options to questions
- Fetch questions and options
- Take quizzes and get scores
- Built with Spring Boot, Spring Data JPA, Lombok, and PostgreSQL

## Technologies

- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- Lombok
- PostgreSQL

## Getting Started

### Prerequisites

- Java 21+
- Maven
- PostgreSQL

### Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/Mayur-Tambe/Online-Quiz-Application.git
   cd Online-Quiz-Application/demo
   ```

2. Configure your database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
   spring.datasource.username=postgres
   spring.datasource.password=root
   ```

3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8081/`

## API Endpoints

### Quiz

- `POST /api/quizzes`  
  Create a new quiz  
  Request body:
  ```json
  {
    "title": "Java Basics",
    "description": "Test your Java knowledge"
  }
  ```

- `GET /api/quizzes`  
  Get all quizzes

- `GET /api/quizzes/{id}`  
  Get quiz by ID

- `DELETE /api/quizzes/{id}`  
  Delete quiz by ID

### Question

- `POST /api/questions`  
  Add a question to a quiz  
  Request body:
  ```json
  {
    "quizId": 1,
    "text": "What is JVM?",
    "type": "MULTIPLE_CHOICE",
    "options": [
      { "text": "Java Virtual Machine", "correct": true },
      { "text": "JavaScript Version Manager", "correct": false }
    ]
  }
  ```

- `GET /api/questions/quiz/{quizId}`  
  Get all questions for a quiz

### Option

- `POST /api/options`  
  Add an option to a question  
  Request body:
  ```json
  {
    "text": "JVM converts bytecode into machine code",
    "correct": true,
    "question": { "id": 1 }
  }
  ```

- `GET /api/options/question/{questionId}`  
  Get all options for a question

### Quiz Taking

- `GET /api/quizzes/{quizId}/questions`  
  Fetch quiz questions for taking (no correct answers)

- `POST /api/quizzes/{quizId}/submit`  
  Submit answers and get score  
  Request body:
  ```json
  [
    {
      "questionId": 1,
      "selectedOptionIds": [2, 3],
      "textAnswer": ""
    },
    {
      "questionId": 2,
      "selectedOptionIds": [],
      "textAnswer": "Your answer"
    }
  ]
  ```
  Response:
  ```json
  {
    "score": 1,
    "total": 2
  }
  ```

## Database

- Uses PostgreSQL
- Tables: quizzes, questions, options

## Configuration

See `src/main/resources/application.properties` for all configuration options.

## Testing

Run tests with:
```sh
mvn test
```

## License

MIT