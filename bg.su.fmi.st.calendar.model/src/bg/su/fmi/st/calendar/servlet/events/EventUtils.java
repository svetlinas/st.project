package bg.su.fmi.st.calendar.servlet.events;

public class EventUtils {
	public static final String PARAMETER_EVENT_ID = "event_id";
	public static final String ATTRIBUTE_EVENT = "event";
	
	public static final String EVENT_VIEW_JSP = "event_view.jsp";
	public static final String EVENT_VIEW_CONTROLLER_URL = "viewEventController";
	
	public static String buildViewEventString(Long eventId){
		return String.format("%s?%s=%d",
				EventUtils.EVENT_VIEW_CONTROLLER_URL,
				EventUtils.PARAMETER_EVENT_ID, eventId);
	}
}
