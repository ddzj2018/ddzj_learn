package open.thl.domain;

public class User {
	private String userName;

	private int age;

	private double score;

	public User() {
		super();
	}

	public User(String userName, int age, double score) {
		super();
		this.userName = userName;
		this.age = age;
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
