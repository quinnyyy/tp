package seedu.tp.commands;

import seedu.tp.flashcard.FlashcardList;
import seedu.tp.ui.Ui;

public class TimelineCommand extends Command {
    private FlashcardList flashcardList;
    private Ui ui;

    public TimelineCommand(FlashcardList flashcardList, Ui ui) {
        this.flashcardList = flashcardList;
        this.ui = ui;
    }

    @Override
    public void execute() {
        LOGGER.info("Listing the flashcards in time order...");
        ui.listAllFlashcardsOrdered(flashcardList);
        LOGGER.info("Listed the flashcards in time order");
    }
}
