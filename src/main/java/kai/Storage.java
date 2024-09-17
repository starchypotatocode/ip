package kai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import kai.tasks.Task;
import kai.ui.Ui;

/**
 * Storage handles the storage and retrieval of Tasks to and from disk.
 * It facilitates loading tasks from a file and saving tasks back to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs Storage with the file path where tasks are stored.
     *
     * @param filePath the path of the file that stores the Task states.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty();
        this.filePath = filePath;
    }

    /**
     * Loads all the Tasks stored in the file into memory.
     *
     * @param parser the KaiParser used to parse the stored task data.
     * @param ui the Ui used to display any loading-related messages.
     * @return an ArrayList of Tasks that were successfully loaded from the file.
     */
    public ArrayList<Task> loadAllTasks(KaiParser parser, Ui ui) {
        try {
            int numFailures = 0;
            int numLines = 0;
            ArrayList<Task> res = new ArrayList<>();

            // Reads file line by line, loading tasks
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                numLines++;
                if (!loadTask(task, parser, res)) {
                    // Handle corrupted task data
                    ui.showLoadingError("\t The loading of Task " + numLines
                            + " failed as it was corrupted in disk.");
                    numFailures++;
                }
            }
            sc.close();

            // Display number of failures, if any
            if (numFailures > 0) {
                ui.showLoadingError("\t In total, " + numFailures + "/"
                        + (numFailures + res.size()) + " tasks failed to load from disk.");
            }
            return res;
        } catch (FileNotFoundException e) {
            // If file is missing, try to handle it by creating a new one
            handleMissingFile(ui);
            return new ArrayList<>(); // Return an empty list when file is missing
        }
    }

    /**
     * Loads an individual Task from a given line of input into the provided TaskList.
     *
     * @param input the string representation of the task.
     * @param parser the KaiParser used to parse the task data.
     * @param taskList the list of tasks to which the parsed task will be added.
     * @return true if the task was loaded successfully, false otherwise.
     */
    private boolean loadTask(String input, KaiParser parser, ArrayList<Task> taskList) {
        try {
            taskList.add(parser.parseStoredTask(input));
            return true; // Task successfully parsed and added
        } catch (IllegalArgumentException
                 | StringIndexOutOfBoundsException
                 | DateTimeParseException e) {
            // Catch any parsing errors, indicating corrupted task data
            return false;
        }
    }

    /**
     * Attempts to create a new save file if one does not exist, and handles any errors that occur.
     * If the save file or directory cannot be created, a warning is shown via the Ui.
     *
     * @param ui the Ui used to display messages related to file creation failures.
     */
    private void handleMissingFile(Ui ui) {
        try {
            File saveFile = new File(filePath);
            File saveDirectory = new File(saveFile.getAbsoluteFile().getParent());

            // Try to create the necessary directories and file
            if (!(saveDirectory.mkdirs() && saveFile.createNewFile())) {
                ui.showLoadingError("\t Warning: There is no save file for Tasks,"
                        + " and one could not be created.");
            }
        } catch (IOException e2) {
            // Catch errors related to file or directory creation
            ui.showLoadingError("\t Warning: There is no save file for Tasks,"
                    + " and one could not be created.");
        }
    }

    /**
     * Saves the current state of all Tasks to the file on disk.
     * The file is first cleared and then each task is written line by line.
     *
     * @param tasks the TaskList containing the tasks to be saved.
     */
    public void save(TaskList tasks) {
        try {
            // Overwrite file content to clear any existing tasks
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();

            // Write each task to the file
            fw = new FileWriter(filePath, true);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.getTask(i).serialize() + System.lineSeparator());
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            // log error to log and/or display to ui?
        }
    }
}
