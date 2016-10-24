package open.thl.google;

import java.util.UUID;

import open.thl.domain.Message;
import open.thl.domain.User;

import com.google.common.eventbus.EventBus;

public class MainObserver {
	
	public static void main(String[] args) {
		EventBus eventBus = new EventBus();
		eventBus.register(new EventObserver());
		Message message=new Message();
		UUID uuid = UUID.randomUUID();  
		message.setId(uuid.toString());
		message.setContent("test obs");
		eventBus.post(message);
		User user=new User();
		user.setAge(10);
		user.setUserName("sdf");
		eventBus.post(user);
	}
	
}
