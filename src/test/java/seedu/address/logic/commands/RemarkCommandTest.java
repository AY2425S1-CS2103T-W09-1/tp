package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        executeRemarkTest(INDEX_FIRST_PERSON, REMARK_STUB, RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        executeRemarkTest(INDEX_FIRST_PERSON, "", RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        executeRemarkTest(INDEX_FIRST_PERSON, REMARK_STUB, RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS);
    }


    private void executeRemarkTest(Index index, String remark, String messageTemplate) {
        Person firstPerson = model.getFilteredPersonList().get(index.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(remark).build();
        RemarkCommand remarkCommand = new RemarkCommand(index, new Remark(remark));
        String expectedMessage = String.format(messageTemplate, editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
        @Test
        public void execute_invalidPersonIndexUnfilteredList_failure() {
            Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
            RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, VALID_REMARK_BOB);
            assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        /**
         * Edit filtered list where index is larger than size of filtered list,
         * but smaller than size of address book
         */
        @Test
        public void execute_invalidPersonIndexFilteredList_failure() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);
            Index outOfBoundIndex = INDEX_SECOND_PERSON;
            // ensures that outOfBoundIndex is still in bounds of address book list
            assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
            RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, VALID_REMARK_BOB);

            assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        @Test
        public void equals() {
            final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_PERSON,
                    VALID_REMARK_AMY);

            // same values -> returns true
            RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON,
                   VALID_REMARK_AMY);
            assertEquals(standardCommand, commandWithSameValues);

            // same object -> returns true
            assertEquals(standardCommand, standardCommand);

            // null -> returns false
            assertNotEquals(null, standardCommand);

            // different types -> returns false
            assertNotEquals(standardCommand, new ClearCommand());

            // different index -> returns false
            assertNotEquals(standardCommand, new RemarkCommand(INDEX_SECOND_PERSON,
                    VALID_REMARK_AMY));

            // different remark -> returns false
            assertNotEquals(standardCommand, new RemarkCommand(INDEX_FIRST_PERSON,
                    VALID_REMARK_BOB));
        }
    }