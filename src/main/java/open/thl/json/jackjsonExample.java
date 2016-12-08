package open.thl.json;

import open.thl.domain.User;

import com.fasterxml.jackson.databind.ObjectMapper;

public class jackjsonExample {
	public static void main(String[] args) {
		ObjectMapper mapper=new ObjectMapper();
		User fromValue = null;
		mapper.convertValue(fromValue, User.class);
	}
}
