package seedu.address.ui;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A panel displaying details of a person stated in a "View" Command
 */
public class ViewPersonPanel extends UiPart<Region> {

    private static final String FXML = "ViewPersonPanel.fxml";
    private Person person;
    @FXML
    private Label name;

    @FXML
    private Label status;

    @FXML
    private Label job;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    @FXML
    private Label skills;

    @FXML
    private Label score;

    /**
     * Details to include inside the panel
     */
    public ViewPersonPanel(Person person) {
        super(FXML);
        this.person = person;
        assert person != null : "Person should not be null";
        setPersonDetails(person);
    }

    public void setPersonDetails(Person person) {
        name.setText(person.getName().fullName);
        job.setText("Job : " + person.getJob().jobName);
        phone.setText("Phone Number : " + person.getPhone().value);
        email.setText("Email Address : " + person.getEmail().value);

        // Convert Set<Skill> to List<String>
        List<String> skillNames = person.getSkills().stream()
                .map(skill -> skill.toString())
                .collect(Collectors.toList());
        skills.setText("Skills : " + String.join(", ", skillNames));

        score.setText("Interview Score : " + person.getInterviewScore());
        Set<Tag> tags = person.getTags();
        if (tags.contains(Person.DEFAULT_TAG_PENDING)) {
            status.setText("Status : " + Person.DEFAULT_TAG_PENDING.tagName);
        } else if (tags.contains(Person.TAG_HIRED)) {
            status.setText("Status : " + Person.TAG_HIRED.tagName);
        } else {
            status.setText("Status : " + Person.TAG_REJECTED.tagName);
        }
    }
}
