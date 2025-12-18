public class User {

	private final String firstName;
	private final String lastName;
	private final int age;
	private final String country;
	private final String state;
	private final boolean active;


	private User(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.country = builder.country;
		this.state = builder.state;
		this.active = builder.active;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getAge() {
		return this.age;
	}

	public String getCountry() {
		return this.country;
	}

	public String getState() {
		return this.state;
	}

	public boolean isActive() {
		return this.active;
	}


	public static class Builder {

		private String firstName;
		private String lastName;
		private int age;
		private String country;
		private String state;
		private boolean active;


		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public Builder isActive(boolean active) {
			this.active = active;
			return this;
		}

		public User build() {
			return new User(this);
		}
	} 
}