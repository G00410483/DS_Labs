package ie.atu.sw;

import java.time.LocalDate;

public record Student(String id, String firstName, String surname, LocalDate dob) {
	public String toString() {
		return new String(firstName + " " + surname);
	}
}