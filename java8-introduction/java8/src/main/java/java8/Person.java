package java8;

public class Person {
	
	public Person (Integer age, String firstname, String lastname) {
		this.age = age;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	private Integer age;
	private String firstname;
	private String lastname;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String owner) {
		this.firstname = owner;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
