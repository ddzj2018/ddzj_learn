package open.thl.domain;

import com.google.common.base.MoreObjects;

public class Message {

	private String id;

	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id",id)
				.add("content",content)
				.toString();
	}
}
