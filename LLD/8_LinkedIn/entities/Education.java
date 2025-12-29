package entities;

import java.time.*;

public class Education {
	private String university;
    private String degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;
    private String grade;

    public Education(String university, String degree, String fieldOfStudy, LocalDate from) {
        this.university = university;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.from = from;
    }

    public Education(String university, String degree, String fieldOfStudy, LocalDate from, LocalDate to) {
        this(university, degree, fieldOfStudy, from);
        this.to = to;
    }

    public Education(String university, String degree, String fieldOfStudy, LocalDate from, LocalDate to, String grade) {
        this(university, degree, fieldOfStudy, from, to);
        this.from = from;
    }
}