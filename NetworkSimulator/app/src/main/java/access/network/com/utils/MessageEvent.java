package access.network.com.utils;

/**
 * Created by Farina Ali
 *
 * This is an event object for passing types of events generated by network choices
 * can be modified according to what we need to display
 */

public class MessageEvent {

    private String eventStatus;

    MessageEvent(String event) {
        eventStatus = event;

    }

    public String getEventStatus() {
        return eventStatus;
    }


}
