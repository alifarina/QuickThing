package access.network.com.utils;

/**
 * Created by infoobjects on 14-06-2017.
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
