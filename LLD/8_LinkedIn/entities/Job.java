package entities;

public class Job {
	private String company;
    private String country;
    private String state;

    public Job(String company) {
        this.company = company;
    }

    public Job(String company, String country) {
        this(company);
        this.country = country;
    }

    public Job(String company, String country, String state) {
        this(company, country);
        this.state = state;
    }
}