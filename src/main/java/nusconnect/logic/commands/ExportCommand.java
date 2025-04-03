package nusconnect.logic.commands;


import java.io.IOError;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import nusconnect.logic.LogicManager;
import nusconnect.logic.commands.exceptions.CommandException;
import nusconnect.model.Model;

/**
 * Exports a new JSON file consisting of new addressbook
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports address book data as a JSON file. "
            + "Parameters: FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + "C:/Users/User/Documents/addressbook.json";

    public static final String MESSAGE_SUCCESS = "Successfully exported address book data: ";
    public static final String MESSAGE_FAILURE = "Failed to export address book data.";

    private final LogicManager logicManager;
    private final String fileString;

    /**
     * @param fileString JSON file path
     * @param logicManager  handles model and storage
     */
    public ExportCommand(String fileString, LogicManager logicManager) {
        this.fileString = fileString;
        this.logicManager = logicManager;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            if (!fileString.endsWith(".json")) {
                throw new CommandException(MESSAGE_FAILURE + "\nInvalid file name!");
            }

            Path filePath = Paths.get(fileString);
            String absoluteFilePath = filePath.toFile().getAbsolutePath();

            logicManager.exportAddressBook(filePath);
            return new CommandResult(MESSAGE_SUCCESS + absoluteFilePath);
        } catch (InvalidPathException | IOError | IOException e) {
            throw new CommandException(MESSAGE_FAILURE + "\nInvalid file path!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return fileString.equals(otherExportCommand.fileString);
    }
}
