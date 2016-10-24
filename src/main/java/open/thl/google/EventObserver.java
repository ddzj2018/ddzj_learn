package open.thl.google;

import open.thl.domain.Message;
import open.thl.domain.User;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

public class EventObserver {
	Gson gson = new Gson();

	@Subscribe
	public void onMessage(Message message) {

		System.out.println(message);
	}

	@Subscribe
	public void onsMessage(User user) {
		
		System.out.println(gson.toJson(user));
		
	}
}