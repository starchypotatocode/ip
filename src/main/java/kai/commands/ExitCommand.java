package kai.commands;

import java.util.Scanner;

import kai.Ui;

/**
 * This Command causes the chatbot to exit
 */
public class ExitCommand extends Command {
    private final Scanner sc;

    /**
     * Constructs an ExitCommand with the resources that need to be cleaned up
     *
     * @param sc the Scanner that needs to be closed
     */
    public ExitCommand(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void invoke(Ui ui) {
        sc.close();
        ui.exit();
    }
}
