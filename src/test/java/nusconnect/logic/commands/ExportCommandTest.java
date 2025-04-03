package nusconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import nusconnect.logic.LogicManager;
import nusconnect.logic.commands.exceptions.CommandException;
import nusconnect.model.Model;

public class ExportCommandTest {

    private final String testFolder = "src/test/data/JsonAddressBookStorageTest/addressbook.json";


    @Test
    public void execute_validFilePath_exportSuccess() throws Exception {
        // Set up mocks
        String validFilePath = testFolder;
        LogicManager mockLogicManager = mock(LogicManager.class);
        Model mockModel = mock(Model.class);

        // Create the ExportCommand
        ExportCommand exportCommand = new ExportCommand(validFilePath, mockLogicManager);

        String absoluteFilePath = Paths.get(validFilePath).toFile().getAbsolutePath();

        // Execute the command
        CommandResult result = exportCommand.execute(mockModel);

        // Verify that the command returns the expected success message
        assertEquals(ExportCommand.MESSAGE_SUCCESS + absoluteFilePath, result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidFileName_exportFailure() throws Exception {

        String invalidFilePath = "f:test";
        LogicManager mockLogicManager = mock(LogicManager.class);
        Model mockModel = mock(Model.class);

        // Create the ExportCommand
        ExportCommand exportCommand = new ExportCommand(invalidFilePath, mockLogicManager);

        // Execute the command and check for CommandException
        try {
            exportCommand.execute(mockModel);
        } catch (CommandException e) {
            assertEquals(ExportCommand.MESSAGE_FAILURE + "\nInvalid file name!", e.getMessage());
        }

    }

    @Test
    public void execute_invalidFilePath_exportFailure() throws Exception {

        String invalidFilePath = "f:test.json";
        LogicManager mockLogicManager = mock(LogicManager.class);
        Model mockModel = mock(Model.class);

        // Create the ExportCommand
        ExportCommand exportCommand = new ExportCommand(invalidFilePath, mockLogicManager);

        // Execute the command and check for CommandException
        try {
            exportCommand.execute(mockModel);
        } catch (CommandException e) {
            assertEquals(ExportCommand.MESSAGE_FAILURE + "\nFile name not provided!", e.getMessage());
        }

    }


}
