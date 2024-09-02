package kai.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void serialize_newToDo_success() {
        ToDo task = new ToDo("");
        ToDo temp = new ToDo("meow");

        assertEquals(task.serialize(), "T | 0 | ");
        assertEquals(temp.serialize(), "T | 0 | meow");

        task.markComplete();
        temp.markComplete();
        assertEquals(task.serialize(), "T | 1 | ");
        assertEquals(temp.serialize(), "T | 1 | meow");

        task.markIncomplete();
        temp.markIncomplete();
        assertEquals(task.serialize(), "T | 0 | ");
        assertEquals(temp.serialize(), "T | 0 | meow");
    }

    @Test
    public void toString_newToDo_success() {
        ToDo task = new ToDo("");
        ToDo temp = new ToDo("meow");

        assertEquals(task.toString(), "[T] [ ] ");
        assertEquals(temp.toString(), "[T] [ ] meow");

        task.markComplete();
        temp.markComplete();
        assertEquals(task.toString(), "[T] [X] ");
        assertEquals(temp.toString(), "[T] [X] meow");

        task.markIncomplete();
        temp.markIncomplete();
        assertEquals(task.toString(), "[T] [ ] ");
        assertEquals(temp.toString(), "[T] [ ] meow");
    }
}
