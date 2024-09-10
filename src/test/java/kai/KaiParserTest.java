package kai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import kai.tasks.Deadline;
import kai.tasks.Event;
import kai.tasks.ToDo;

public class KaiParserTest {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    @Test
    public void parseStoredTask_validTasks() {
        ToDo toDo1 = new ToDo(" ");
        ToDo toDo2 = new ToDo("woof");
        assertEquals(new KaiParser().parseStoredTask("T | 0 |  "), toDo1);
        assertEquals(new KaiParser().parseStoredTask("T | 0 | woof"), toDo2);

        toDo1.setComplete();
        toDo2.setComplete();
        assertEquals(new KaiParser().parseStoredTask("T | 1 |  "), toDo1);
        assertEquals(new KaiParser().parseStoredTask("T | 1 | woof"), toDo2);

        Deadline deadline1 = new Deadline(" ", LocalDate.of(2015, 3, 16));
        Deadline deadline2 = new Deadline("Jump", LocalDate.of(2000, 5, 5));
        assertEquals(new KaiParser().parseStoredTask("D | 0 |   | "
                + LocalDate.of(2015, 3, 16).format(STORAGE_FORMATTER)), deadline1);
        assertEquals(new KaiParser().parseStoredTask("D | 0 | Jump | "
                + LocalDate.of(2000, 5, 5).format(STORAGE_FORMATTER)), deadline2);

        deadline1.setComplete();
        deadline2.setComplete();
        assertEquals(new KaiParser().parseStoredTask("D | 1 |   | "
                + LocalDate.of(2015, 3, 16).format(STORAGE_FORMATTER)), deadline1);
        assertEquals(new KaiParser().parseStoredTask("D | 1 | Jump | "
                + LocalDate.of(2000, 5, 5).format(STORAGE_FORMATTER)), deadline2);

        Event event1 = new Event("0", LocalDate.of(2013, 7, 16),
                LocalDate.of(2013, 7, 16));
        Event event2 = new Event("BARK", LocalDate.of(2000, 5, 5),
                LocalDate.of(2005, 8, 8));
        assertEquals(new KaiParser().parseStoredTask("E | 0 | 0 | "
                + LocalDate.of(2013, 7, 16).format(STORAGE_FORMATTER) + " | "
                + LocalDate.of(2013, 7, 16).format(STORAGE_FORMATTER)), event1);
        assertEquals(new KaiParser().parseStoredTask("E | 0 | BARK | "
                + LocalDate.of(2000, 5, 5).format(STORAGE_FORMATTER) + " | "
                + LocalDate.of(2005, 8, 8).format(STORAGE_FORMATTER)), event2);

        event1.setComplete();
        event2.setComplete();
        assertEquals(new KaiParser().parseStoredTask("E | 1 | 0 | "
                + LocalDate.of(2013, 7, 16).format(STORAGE_FORMATTER) + " | "
                + LocalDate.of(2013, 7, 16).format(STORAGE_FORMATTER)), event1);
        assertEquals(new KaiParser().parseStoredTask("E | 1 | BARK | "
                + LocalDate.of(2000, 5, 5).format(STORAGE_FORMATTER) + " | "
                + LocalDate.of(2005, 8, 8).format(STORAGE_FORMATTER)), event2);
    }
}
