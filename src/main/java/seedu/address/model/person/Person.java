package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    public static final Tag DEFAULT_TAG_PENDING = new Tag("pending");
    public static final Tag TAG_HIRED = new Tag("hired");
    public static final Tag TAG_REJECTED = new Tag("rejected");

    // Identity fields
    private final Name name;
    private final Job job;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Status fields
    private String status;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Job job, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, job, phone, email, address, tags);
        this.name = name;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        if (!isHired()) {
            this.tags.add(DEFAULT_TAG_PENDING);
        }
    }

    public Name getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }
    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void markAsHired() {
        this.status = "hired";
        removeTag(DEFAULT_TAG_PENDING);
        addTag(TAG_HIRED);
    }

    public boolean isHired() {
        return tags.contains(TAG_HIRED);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && job.equals(otherPerson.job)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    public boolean hasJobAndStatus(Name name, Job job) {
        return this.getName().equals(name) && this.getJob().equals(job);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, job, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("job", job)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
