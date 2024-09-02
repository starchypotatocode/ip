package kai.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void serialize_newEvent_success() {
        Deadline task = new Deadline("",
                LocalDate.of(2009, 6, 11));
        Deadline temp = new Deadline("meow",
                LocalDate.of(1899, 10, 4));

        assertEquals(task.serialize(), "D | 0 |  | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 0 | meow | Oct 04 1899");

        task.markComplete();
        temp.markComplete();
        assertEquals(task.serialize(), "D | 1 |  | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 1 | meow | Oct 04 1899");

        task.markIncomplete();
        temp.markIncomplete();
        assertEquals(task.serialize(), "D | 0 |  | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 0 | meow | Oct 04 1899");
    }

    @Test
    public void toString_newEvent_success() {
        Deadline task = new Deadline("",
                LocalDate.of(2009, 6, 11));
        Deadline temp = new Deadline("meow",
                LocalDate.of(1899, 10, 4));

        assertEquals(task.toString(), "[E] [ ]  (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[E] [ ] meow (by: Oct 04 1899)");

        task.markComplete();
        temp.markComplete();
        assertEquals(task.toString(), "[E] [X]  (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[E] [X] meow (by: Oct 04 1899)");

        task.markIncomplete();
        temp.markIncomplete();
        assertEquals(task.toString(), "[E] [ ]  (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[E] [ ] meow (by: Oct 04 1899)");
    }
}
