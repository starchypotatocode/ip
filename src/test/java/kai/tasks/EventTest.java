package kai.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void serialize() {
        Event task = new Event("z",
                LocalDate.of(2009, 12, 11), LocalDate.of(2010, 1, 9));
        Event temp = new Event("meow",
                LocalDate.of(1999, 12, 20), LocalDate.of(2030, 9, 1));

        assertEquals(task.serialize(), "E | 0 | z | Dec 11 2009 | Jan 09 2010");
        assertEquals(temp.serialize(), "E | 0 | meow | Dec 20 1999 | Sep 01 2030");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.serialize(), "E | 1 | z | Dec 11 2009 | Jan 09 2010");
        assertEquals(temp.serialize(), "E | 1 | meow | Dec 20 1999 | Sep 01 2030");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.serialize(), "E | 0 | z | Dec 11 2009 | Jan 09 2010");
        assertEquals(temp.serialize(), "E | 0 | meow | Dec 20 1999 | Sep 01 2030");
    }

    @Test
    public void toString_all() {
        Event task = new Event("  ",
                LocalDate.of(2009, 12, 11), LocalDate.of(2010, 1, 9));
        Event temp = new Event("meow",
                LocalDate.of(1999, 12, 20), LocalDate.of(2030, 9, 1));

        assertEquals(task.toString(), "[E] [ ]    (from: Dec 11 2009 to: Jan 09 2010)");
        assertEquals(temp.toString(), "[E] [ ] meow (from: Dec 20 1999 to: Sep 01 2030)");

        task.setComplete();
        temp.setComplete();
        assertEquals(task.toString(), "[E] [X]    (from: Dec 11 2009 to: Jan 09 2010)");
        assertEquals(temp.toString(), "[E] [X] meow (from: Dec 20 1999 to: Sep 01 2030)");

        task.setIncomplete();
        temp.setIncomplete();
        assertEquals(task.toString(), "[E] [ ]    (from: Dec 11 2009 to: Jan 09 2010)");
        assertEquals(temp.toString(), "[E] [ ] meow (from: Dec 20 1999 to: Sep 01 2030)");
    }
}
