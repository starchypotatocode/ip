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
 * Storage handles the storage and retrieval of Tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs Storage with the appropriate path to retrieve or store Tasks in.
     *
     * @param filePath the path of the file the Task states are found in.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty();
        this.filePath = filePath;
    }

    /**
     * Loads all the Tasks found in the file to memory.
     *
     * @param parser the KaiParser that is used to parse the input.
     * @param ui the Ui that is used to display messages.
     * @return ArrayList of Tasks appropriately configured.
     */
    public ArrayList<Task> loadAllTasks(KaiParser parser, Ui ui) {
        try {
            int numFailures = 0;
            int numLines = 0;
            ArrayList<Task> res = new ArrayList<>();

            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                numLines++;
                if (!loadTask(task, parser, res)) {
                    ui.showLoadingError("\t The loading of Task "
                            + numLines + " failed as it was corrupted in disk.");
                    numFailures++;
                }
            }
            sc.close();

            if (numFailures > 0) {
                ui.showLoadingError("\t In total, " + numFailures + "/"
                        + (numFailures + res.size()) + " tasks failed to load from disk.");
            }
            return res;
        } catch (FileNotFoundException e) {
            handleMissingFile(ui);
            return new ArrayList<>();
        }
    }

    /**
     * Loads the Task based off the line of input given
     * into the TaskList.
     *
     * @param input the input in question.
     * @param parser the KaiParser that is used to parse the input.
     * @param taskList the ArrayList of tasks in question.
     * @return whether the loading was successful.
     */
    private boolean loadTask(String input, KaiParser parser, ArrayList<Task> taskList) {
        try {
            taskList.add(parser.parseStoredTask(input));
            return true;
        } catch (IllegalArgumentException
                 | StringIndexOutOfBoundsException
                 | DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Tries to create a new savefile if one does not exist,
     * and returns errors accordingly if that is not possible.
     *
     * @param ui the Ui that is used to display messages.
     */
    private void handleMissingFile(Ui ui) {
        try {
            File saveFile = new File(filePath);
            File saveDirectory = new File(saveFile.getAbsoluteFile().getParent());

            if (!(saveDirectory.mkdirs() && saveFile.createNewFile())) {
                ui.showLoadingError("\t Warning: There is no save file for Tasks,"
                        + " and one could not be created.");
            }
        } catch (IOException e2) {
            ui.showLoadingError("\t Warning: There is no save file for Tasks,"
                    + " and one could not be created.");
        }
    }

    /**
     * Saves the current Tasks to the file on disk.
     *
     * @param tasks the Tasks to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(""); // clean out the file for saving
            fw.close();

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
