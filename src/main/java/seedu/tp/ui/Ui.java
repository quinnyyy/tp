package seedu.tp.ui;

import seedu.tp.exceptions.InvalidDateFormatException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.parser.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static seedu.tp.utils.Constants.DETAIL_FIELD;
import static seedu.tp.utils.Constants.EMPTY_STRING;

/**
 * Ui class.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Sends welcome message to user.
     */
    public void sendWelcomeMessage() {
        System.out.println("Welcome to History Flashcard App!");
    }

    /**
     * Sends bye message to user.
     */
    public void sendByeMessage() {
        System.out.println("Thanks for using History Flashcard!");
    }

    /**
     * Sends help message to user.
     */
    public void sendHelpMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Followings are the formats of commands used in the main menu:")
            .append(System.lineSeparator());
        stringBuilder.append("Add an event flashcard: event").append(System.lineSeparator());
        stringBuilder.append("Add a person flashcard: person").append(System.lineSeparator());
        stringBuilder.append("Add an other flashcard: other").append(System.lineSeparator());
        stringBuilder.append("List out all the flashcards: list").append(System.lineSeparator());
        stringBuilder.append("Delete a flashcard from the flashcard list: delete i/INDEX")
            .append(System.lineSeparator());
        stringBuilder.append("Set up a new flashcard group: group").append(System.lineSeparator());
        stringBuilder.append("Add a flashcard to an existing group: add").append(System.lineSeparator());
        stringBuilder.append("To exit the History Flashcard: bye").append(System.lineSeparator());
        stringBuilder.append("To get help message: help").append(System.lineSeparator());
        stringBuilder.append("Then please follow the instruction given by the program in each situation.");
        System.out.println(stringBuilder);
    }

    /**
     * Prompts the user for a list of details for a flashcard.
     *
     * @return the list of details entered by user
     */
    public List<String> promptUserForDetails() {
        List<String> details = new ArrayList<>();
        Optional<String> newDetailOptional = promptUserForOptionalField(DETAIL_FIELD);
        while (newDetailOptional.isPresent()) {
            details.add(newDetailOptional.get());
            newDetailOptional = promptUserForOptionalField(DETAIL_FIELD);
        }
        return details;
    }

    /**
     * Prompts the user for a piece of optional data used in the construction of a <code>Flashcard</code>.
     * The user can leave the line empty.
     *
     * @param fieldName string representing the name of the data to prompt for
     * @return the user's input
     */
    public Optional<String> promptUserForOptionalField(String fieldName) {
        System.out.println("Please enter " + fieldName + " (optional):");
        String input = getNextLine().trim();
        return input.equals(EMPTY_STRING) ? Optional.empty() : Optional.of(input);
    }

    /**
     * Prompts the user for a piece of required data used in the construction of a <code>Flashcard</code>.
     *
     * @param fieldName string representing the name of the data to prompt for
     * @return the user's input
     */
    public String promptUserForRequiredField(String fieldName) {
        System.out.println("Please enter " + fieldName + ":");
        String input = getNextLine().trim();
        while (input.equals(EMPTY_STRING)) {
            System.out.println("That is a required field! Please enter again.");
            input = getNextLine().trim();
        }
        return input;
    }

    /**
     * Prompts the user for an optional date used in the construction of a <code>Flashcard</code>.
     *
     * @param fieldName string representing name of the date to prompt for
     * @return the parsed date
     */
    public Optional<LocalDate> promptUserForOptionalLocalDate(String fieldName) {
        System.out.println("Please enter " + fieldName + " (optional):");
        String input;
        LocalDate localDate = null;

        do {
            input = getNextLine().trim();
            if (input.equals(EMPTY_STRING)) {
                return Optional.empty();
            }
            try {
                localDate = Parser.parseDate(input);
            } catch (InvalidDateFormatException e) {
                System.out.println("That date format couldn't be parsed! Please enter again.");
            }
        } while (localDate == null);

        return Optional.of(localDate);
    }

    /**
     * Prompts the user for a required date used in the construction of a <code>Flashcard</code>.
     *
     * @param fieldName string representing name of the date to prompt for
     * @return the parsed date
     */
    public LocalDate promptUserForRequiredLocalDate(String fieldName) {
        System.out.println("Please enter " + fieldName + ":");
        String input;
        LocalDate localDate = null;

        do {
            input = getNextLine().trim();
            try {
                localDate = Parser.parseDate(input);
            } catch (InvalidDateFormatException e) {
                System.out.println("That date format couldn't be parsed! Please enter again.");
            }
        } while (localDate == null);

        return localDate;
    }

    /**
     * Sends flashcard creation confirmation to user.
     *
     * @param flashcard the flashcard created
     */
    public void confirmFlashcardCreation(Flashcard flashcard) {
        System.out.println("You've successfully created the flashcard below:");
        System.out.println(flashcard);
    }

    /**
     * Prints confirmation that flashcard has been marked as reviewed.
     *
     * @param flashcard the flashcard that was reviewed
     */
    public void confirmFlashcardReview(Flashcard flashcard) {
        System.out.println("You have marked the following flashcard as Reviewed:");
        System.out.println(flashcard.getName());
    }

    /**
     * Prints confirmation that flashcard priority level has been updated.
     *
     * @param flashcard the flashcard that had its priority updated
     */
    public void confirmFlashcardPriority(Flashcard flashcard) {
        System.out.println("Priority has been updated:");
        System.out.println(flashcard.getName() + " | New priority: " + flashcard.getPriorityAsString());
    }

    /**
     * Displays flashcard details according to index specified.
     *
     * @param flashcard the flashcard to be displayed
     */
    public void showFlashcard(Flashcard flashcard) {
        System.out.println("These are the flashcard details:");
        System.out.println(flashcard);
    }

    /**
     * Sends flashcard group creation confirmation to user.
     *
     * @param flashcardGroup the flashcard group created
     */
    public void confirmFlashcardGroupCreation(FlashcardGroup flashcardGroup) {
        System.out.println("You've successfully created the group below:");
        System.out.println(flashcardGroup);
    }

    /**
     * Sends confirmation message that the flashcard is successfully added to a group.
     * *
     *
     * @param flashcardGroup the flashcard group the flashcard is added into.
     * @param flashcard      the flashcard just be added into the group
     */
    public void confirmFlashcardAdditionToGroup(FlashcardGroup flashcardGroup, Flashcard flashcard) {
        System.out.println("You've successfully add the flashcard below:");
        System.out.println(flashcard);
        System.out.println("To the group:");
        System.out.println(flashcardGroup);
    }

    /**
     * Prints out all flashcards in the list.
     *
     * @param flashcardList the list of flashcards to be printed out
     */
    public void listAllFlashcards(FlashcardList flashcardList) {
        if (flashcardList.isEmpty()) {
            System.out.println("You have no flashcard at this moment!");
            return;
        }

        System.out.println("Here's the list of flashcards you have:");
        for (int i = 0; i < flashcardList.getTotalFlashcardNum(); i++) {
            Flashcard flashcard = flashcardList.getFlashcardAtIdx(i);
            System.out.println((i + 1) + ": " + flashcard.getName() + " | Reviewed: " + flashcard.getReviewIcon()
                + " | " + flashcard.getPriorityAsString());
        }
    }

    /**
     * Prints out all flashcards in the list ordered by start/birth date. Other cards come last
     *
     * @param flashcardList the list of flashcards to be printed out
     */
    public void listAllFlashcardsOrdered(FlashcardList flashcardList) {
        if (flashcardList.isEmpty()) {
            System.out.println("You have no flashcards at this moment!");
            return;
        }

        List<Flashcard> flashcards = new ArrayList<>(flashcardList.getFlashcards());
        Collections.sort(flashcards);
        System.out.println("Here's a sorted list of the flashcards you have:");
        for (Flashcard f : flashcards) {
            System.out.println(f);
        }
    }

    /**
     * Prints out exception to UI.
     *
     * @param exception the exception to be printed out
     */
    public void printException(Exception exception) {
        System.out.println("An exception has occurred!");
        System.out.println(exception.getMessage());
    }

    /**
     * Sends response to unknown command entered by user.
     */
    public void sendUnknownCommandResponse() {
        System.out.println("Sorry, I don't know how to help with that yet.");
    }

    /**
     * Sends response to invalid flashcard index entered by user.
     */
    public void sendInvalidFlashcardIndexResponse() {
        System.out.println("The flashcard index you entered is invalid");
    }

    /**
     * Gets the next user input line.
     *
     * @return next line
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

}
