# ✅ CI for Note App (Gradle + GitHub Actions)

This update adds automated testing to the project using **GitHub Actions** and simple **Spring Boot tests**.

##  Tests

I added two test classes under `src/test/java`:

### `ApplicationTests`
```java
package com.goit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }
}
```
This test verifies that the Spring application context starts successfully.

### `NoteServiceImplTest`
```java
package com.goit.spring.service;

import com.goit.spring.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NoteServiceImplTest {

    @Autowired
    private NoteService noteService;

    @Test
    void add_and_getById() {
        Note n = new Note();
        n.setTitle("t1");
        n.setContent("c1");

        Note saved = noteService.add(n);
        assertNotNull(saved.getId());

        Note fromDb = noteService.getById(saved.getId());
        assertEquals("t1", fromDb.getTitle());
        assertEquals("c1", fromDb.getContent());
    }
}
```

This test lifts the real Spring context, uses the real `NoteService` and asserts that create + read works.

test ci