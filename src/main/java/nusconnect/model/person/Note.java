package nusconnect.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's note in the address book.
 * Guarantees: immutable
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank!";

    /*
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Note}.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    /**
     * Returns true if a given Note is a valid note.
     */
    public static boolean isValidNote(Note test) {
        return test.value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}



