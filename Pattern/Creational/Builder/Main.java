public class Main {
	public static void main(String[] args) {
		User user = new User.Builder()
                .firstName("A")
                .lastName("B")
                .age(100)
                .isActive(true)
                .build();

		System.out.println(user.getFirstName() + user.getLastName());
	}
}