package seedu.tp.commands;

import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_DATE;
import static seedu.tp.utils.ExampleInputConstants.FULL_SIMULATED_INPUT;
import static seedu.tp.utils.ExampleInputConstants.NAME;
import static seedu.tp.utils.ExampleInputConstants.START_DATE;
import static seedu.tp.utils.ExampleInputConstants.SUMMARY;
import static seedu.tp.utils.InputTestUtil.getFlashcardFactoryWithInput;

public class EventFlashcardCommandTest {
    @Test
    public void eventFlashcardCommand_execute_addsFlashcardSuccessfully() throws UnrecognizedFlashcardTypeException {
        FlashcardList expectedFlashcardList = new FlashcardList();
        expectedFlashcardList.addFlashcard(new EventFlashcard(NAME, START_DATE, END_DATE, SUMMARY, DETAILS));

        FlashcardList actualFlashcardList = new FlashcardList();
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(FULL_SIMULATED_INPUT);
        EventFlashcardCommand eventFlashcardCommand = new EventFlashcardCommand(actualFlashcardList, flashcardFactory);
        eventFlashcardCommand.execute();

        assertEquals(expectedFlashcardList, actualFlashcardList);
        assertTrue(actualFlashcardList.getFlashcardAtIdx(0) instanceof EventFlashcard);
    }
}