import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage handles the storage and retrieval of Tasks
 */
public class Storage {
    private String filePath;

    /**
     * Constructs Storage with the appropriate path to retrieve or store Tasks in
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all the Tasks found in the file to memory
     *
     * @return ArrayList of Tasks appropriately configured
     */
    public ArrayList<Task> load() {
        try {
            int numFailures = 0;
            int numLines = 0;
            ArrayList<Task> res = new ArrayList<>();

            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                numLines++;

                try {
                    res.add(Parser.parseStoredTask(task));
                } catch (IllegalArgumentException e) {
                    numFailures++;
                    Ui.showLoadingError("\t The loading of Task " + numLines + " failed as it was corrupted in disk.");
                }
            }

            sc.close();
            if (numFailures > 0) {
                Ui.showLoadingError("\t In total, " + numFailures + "/" + (numFailures + res.size()) +
                        " tasks failed to load from disk.");
            }
            return res;
        } catch (FileNotFoundException e) {
            try {
                if (!new File(filePath).createNewFile()) {
                    Ui.showLoadingError("\t Warning: There is no save file for Tasks, and one could not be created.");
                }
            } catch (IOException e2) {
                Ui.showLoadingError("\t Warning: There is no save file for Tasks, and one could not be created.");
            }
            return new ArrayList<>();
        }
    }

    /**
     * Saves the current Tasks to the file on disk
     *
     * @param tasks the Tasks to be saved
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(""); // clean out the file for saving
            fw.close();

            fw = new FileWriter(filePath, true);
            for (Task task : tasks) {
                fw.write(task.serialize() + System.lineSeparator());
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {

        }
    }
}
