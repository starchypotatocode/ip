package kai.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void serialize() {
        Deadline task = new Deadline(" ",
                LocalDate.of(2009, 6, 11));
        Deadline temp = new Deadline("meow",
                LocalDate.of(1899, 10, 4));

        assertEquals(task.serialize(), "D | 0 |   | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 0 | meow | Oct 04 1899");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.serialize(), "D | 1 |   | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 1 | meow | Oct 04 1899");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.serialize(), "D | 0 |   | Jun 11 2009");
        assertEquals(temp.serialize(), "D | 0 | meow | Oct 04 1899");
    }

    @Test
    public void toString_all() {
        Deadline task = new Deadline("a",
                LocalDate.of(2009, 6, 11));
        Deadline temp = new Deadline("meow",
                LocalDate.of(1899, 10, 4));

        assertEquals(task.toString(), "[D] [ ] a (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[D] [ ] meow (by: Oct 04 1899)");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.toString(), "[D] [X] a (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[D] [X] meow (by: Oct 04 1899)");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.toString(), "[D] [ ] a (by: Jun 11 2009)");
        assertEquals(temp.toString(), "[D] [ ] meow (by: Oct 04 1899)");
    }
}
