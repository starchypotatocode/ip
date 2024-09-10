package kai.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void serialize() {
        ToDo task = new ToDo("   ");
        ToDo temp = new ToDo("meow");

        assertEquals(task.serialize(), "T | 0 |    ");
        assertEquals(temp.serialize(), "T | 0 | meow");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.serialize(), "T | 1 |    ");
        assertEquals(temp.serialize(), "T | 1 | meow");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.serialize(), "T | 0 |    ");
        assertEquals(temp.serialize(), "T | 0 | meow");
    }

    @Test
    public void toString_all() {
        ToDo task = new ToDo("   ");
        ToDo temp = new ToDo("meow");

        assertEquals(task.toString(), "[T] [ ]    ");
        assertEquals(temp.toString(), "[T] [ ] meow");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.toString(), "[T] [X]    ");
        assertEquals(temp.toString(), "[T] [X] meow");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.toString(), "[T] [ ]    ");
        assertEquals(temp.toString(), "[T] [ ] meow");
    }
}
