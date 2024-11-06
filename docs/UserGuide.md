---
layout: default.md
title: "User Guide"
pageNav: 3
---

# HiredFiredPro User Guide

HiredFiredPro is a **desktop app for managing interviews, optimized for use via a Command  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HiredFiredPro can get your interview management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## About This Document

This section introduces the HiredFiredPro layout, command format and commonly used parameters to help you understand the document.

* If you are interested in setting up and using HiredFiredPro, head over to the [Quick Start](#quick-start) section.
* If you are interested in learning more about the features, you can visit the [Features](#features) section.

### HiredFiredPro Layout

The image below shows the different components of HiredFiredPro.

### Command Format

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Commonly Used Parameters

<box type="info" seamless>

**Notes about the parameters:**<br>

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  </box>

Parameter     | Notes
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**`NAME`**   | restrictions
**`JOB`**    | restrictions
**`PHONE_NUMBER`**   | restrictions
**`EMAIL`** | restrictions
**`SKILL`**   | restrictions
**`INTERVIEW_SCORE`**   | restrictions
**`TAG`**   | restrictions
**`INDEX`**   | restrictions
**`ORDER`**   | restrictions

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

2. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103T-W09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for HiredFiredPro.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hiredfiredpro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br><br>
   ![Ui](images/Ui.png) 


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all candidates.

   * `add n/John Doe j/Software Developer p/98765432 e/johnd@example.com i/7.5` : Adds a candidate named `John Doe` to HiredFiredPro.

   * `delete 3` : Deletes the 3rd candidate shown in the current list.

   * `clear` : Deletes all candidates.

   * `exit` : Exits the HiredFiredPro app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to HiredFiredPro.

Format: `add n/NAME j/JOB p/PHONE_NUMBER e/EMAIL [s/SKILL]… i/INTERVIEW_SCORE  [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags and skills (including 0)
</box>

Examples:
* `add n/John Doe j/Software Engineer p/98765432 e/johnd@example.com i/5.0`
* `add n/Betsy Crowe j/Software Tester t/friend e/betsycrowe@example.com  p/1234567 i/7 s/Python s/Java`

### Listing all persons : `list`

Shows a list of all persons in HiredFiredPro.

Format: `list`

### Editing a person : `edit`

Edits an existing person in HiredFiredPro.

Format: `edit INDEX [n/NAME] [j/JOB] [p/PHONE] [e/EMAIL] [s/SKILL]… [i/INTERVIEW_SCORE] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* When editing skills, the existing skills of the person will be removed i.e. adding of skills is not cumulative.
* You can remove all the person’s skills by typing `s/` without
  specifying any skills after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Hiring a person by name and job: `hire`

Changes an existing person's status to "Hired" in HiredFiredPro.

Format: `hire n/NAME j/JOB`

* Changes the person's status to `Hired`.
* Both the name of the person and the job that he/she is applying for must be provided.
* Existing status will be updated to the `Hired` status.
* The search is case-insensitive for both name and job. e.g. `hans` will match `Hans` and `software engineer` will match
  `Software Engineer`.
* Only full words will be matched e.g. `Han` will not match `Hans` and `software engineers` will not match
  `Software Engineer`.


### Rejecting a person by name and job: `reject`

Changes an existing person's status to "Rejected" in HiredFiredPro.

Format: `reject n/NAME j/JOB`

* Changes the person's status to `Rejected`.
* Both the name of the person and the job that he/she is applying for must be provided.
* Existing status will be updated to the `Rejected` status.
* The search is case-insensitive for both name and job. e.g. `hans` will match `Hans` and `software engineer` will match
  `Software Engineer`.
* Only full words will be matched e.g. `Han` will not match `Hans` and `software engineers` will not match
  `Software Engineer`.

### View a person's status by name and job: `view`

Shows a person's status in HiredFiredPro.

Format: `view n/NAME j/JOB`

* Views the person's status (Hired / Rejected / Pending).
* Both the name of the person and the job that he/she is applying for must be provided.
* The search is case-insensitive for both name and job. e.g. `hans` will match `Hans` and `software engineer` will match
  `Software Engineer`.
* Only full words will be matched e.g. `Han` will not match `Hans` and `software engineers` will not match
  `Software Engineer`.
* Details of the person being viewed will be displayed in the display panel.

Examples:
* `view n/Alex Yeoh j/Software Engineer` displays the details of candidate Alex Yeoh in the display panel.


* ![display-panel](images/Display-Panel.png)

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from HiredFiredPro.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in HiredFiredPro.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Sorting persons by interview scores : `sort`

Sorts the list of candidates in ascending or descending order based on their interview scores.

Format: `sort ORDER`

* `ORDER` can be either `a`(ascending) or `d`(descending).

Examples:
* `list` followed by `sort a` sorts the entire list of candidates in ascending order based on their interview scores.
* `find Alice Betsy Charlie` followed by `sort d` sorts the resulting candidate list of the `find` command in descending order based on their interview scores.

### Clearing all entries : `clear`

Clears all entries from HiredFiredPro.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HiredFiredPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HiredFiredPro data are saved automatically as a JSON file `[JAR file location]/data/hiredfiredpro.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, HiredFiredPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause HiredFiredPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HiredFiredPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**List**   | `list`
**Add**    | `add n/NAME j/JOB p/PHONE_NUMBER e/EMAIL [s/SKILL]… i/INTERVIEW_SCORE  [t/TAG]…​` <br> e.g., `add n/James Ho j/Site Reliability Engineer p/22224444 e/jamesho@example.com s/python s/java i/8.5 t/friend`
**Edit**   | `edit INDEX [n/NAME] [j/JOB] [p/PHONE] [e/EMAIL] [s/SKILL]… [i/INTERVIEW_SCORE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Hire**   | `hire n/NAME j/JOB`<br> e.g., `hire n/James Jake j/Software Engineer`
**Reject**   | `reject n/NAME j/JOB` <br> e.g., `reject n/James Jake j/Software Engineer`
**View**   | `view n/NAME j/JOB` <br>e.g., `view n/James Jake j/Software Engineer`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Sort**   | `sort ORDER`<br> e.g., `sort a`
**Help**   | `help`
**Clear**  | `clear`
**Exit**  | `exit`

