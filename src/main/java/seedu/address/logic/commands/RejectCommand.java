package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Changes the status of a candidate to rejected.
 */
public class RejectCommand extends Command {

    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of a candidate to rejected. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_JOB + "JOB\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jane Smith "
            + PREFIX_JOB + "Data Analyst";

    public static final String MESSAGE_REJECT_PERSON_SUCCESS =
            "Candidate %1$s has been successfully marked as rejected.";
    public static final String MESSAGE_ALREADY_REJECTED = "Error: Candidate %1$s is already marked as rejected.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "This candidate does not exist in the address book.";

    private final Name name;
    private final Job job;

    /**
     * Creates a RejectCommand to change the status of the specified {@code Person}
     */
    public RejectCommand(Name name, Job job) {
        requireNonNull(name);
        requireNonNull(job);
        this.name = name;
        this.job = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToReject = model.findPersonByNameAndJob(name, job);

        if (personToReject == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        if (personToReject.isRejected()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_REJECTED, name));
        }
        personToReject.markAsRejected();
        return new CommandResult(String.format(MESSAGE_REJECT_PERSON_SUCCESS,
                personToReject.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RejectCommand otherCommand)) {
            return false;
        }

        return name.equals(otherCommand.name)
                && job.equals(otherCommand.job);
    }
}
