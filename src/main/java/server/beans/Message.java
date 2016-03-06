package server.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Message bean
 *
 * Contains command string and command data as a nested object.
 *
 * Created by Johan on 2016-03-05.
 */
public class Message<T> {
	private String command;
	private List<T> commandData;

	public Message(String command){
		this.command = command;
		commandData = new ArrayList<>();
	}

	public void addCommandData(T currData){
		commandData.add(currData);
	}
}
