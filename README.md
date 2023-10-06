
## Description
Repository contains code sample for the Spring presentation.

Consists of 4 modules:
- naive-approach (the simplest method of building OOP logic in java)
- object-factory-approach (using object factory patter to improve code architecture)
- spring-approach (using Spring Framework features to improve the same code)
- article-analyzer (home task)

---
## Presentation
https://docs.google.com/presentation/d/1ZBtkKpfy27YA95BDzxTV37dDAWdOqnBweHW45WFLxT8/edit?usp=sharing

---

## Task: Article analyzer

### Business requirements

1. **Read all articles from the json file**
2. **Process `content` field of each article and analyze which category it is**
3. **After analyzing of all articles, save set of unique categories into `categories.json` file**
4. **Notify users with the result**

Details:

**Read Articles:**

- read articles from `articles.json` file
- map json string into `Article.java` class using Gson library

**Process algorithm:**
- split the whole content string into separate words by space (" ") and get the most repeated ones
- exclude helper words (is, are, can, do, be, have, has, had, was, will, would, should, we, a, an, the, and, or, also, as, all, of, this, these, there, no, if, in)\
    note: this set of words should be placed into `application.properties`
- case should be ignored (can be in lower and upper)
- if there are two or more the same count of most repeated words, choose all of these words as categories

**Saving categories:**
- `categories.json` file should be located under `resources` folder.
- if there is no such file, create the one
- map result categories set into json string and save into the file
- all categories in the file should be unique

**Notifying users:**
- read users from `users.json` file
- map json string into `User.java` class using Gson library
- user has `notificationType` field by which you should decide how to notify user (email or slack - should be mapped into enum)
- you should have two different service classes for two different notification types (EmailNotificationService, SlackNotificationService - example is in sample codes)
- notification means printing logs into the console (what to print is up to you)

### Technical requirements

- application should use Spring Boot and benefit from Dependency Injection.
- property file should be used.
- configuration class should be used.
- code should adhere to Single Responsibility Principle.

### How to complete the task
- fork the repository
- generate spring boot code in https://start.spring.io/ (you can use lombock as dependency as well)
- unarchive the generated code into `article-analyzer` folder
- write the code
- create pull request into this repository from your forked one
- your PR will be reviewed and some suggestions might be given
- if all looks good from the reviewer point of view, **PR will be closed and the assigment is considered as completed**.
