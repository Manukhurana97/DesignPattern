package entities;

import java.util.*;

public class Profile {
    private String description;
    private List<Education> educations;
    private List<Job> jobs;

    public Profile(String description, List<Education> educations, List<Job> jobs) {
        this.description = description;
        this.educations = educations;
        this.jobs = jobs;
    }

    public String getDescription() { return description; }
    public List<Education> getEducations() { return educations; }
    public List<Job> getJobs() { return jobs; }
}
