package bg.su.fmi.st.calendar.servlet.events;

public class EventUtils {
	public static final String PARAMETER_EVENT_ID = "event_id";
	public static final String PARAMETER_EVENT_TITLE = "event_title";
	public static final String PARAMETER_EVENT_PLACE = "event_place";
	public static final String PARAMETER_EVENT_TYPE = "event_type"; //only private and public are allowed
	public static final String PARAMETER_EVENT_DETAILS = "event_details";
	public static final String PARAMETER_EVENT_START_DATE = "event_start_date";
	public static final String PARAMETER_EVENT_END_DATE = "event_end_date";
	
	public static final String PARAMETER_USERNAME = "username";
	public static final String PARAMETER_INVITATION_ID = "invitation_id";
	
	public static final String INVITATION_COMMENT = "comment";

	public static final String ATTRIBUTE_EVENT = "event";
	public static final String ATTRIBUTE_EVENT_INVITATION_LIST = "eventInvitationList";
	public static final String ATTRIBUTE_IS_OWNER = "isOwner";

	public static final String URL_VIEW_EVENT_JSP = "event_view.jsp";
	public static final String URL_VIEW_EVENT_CONTROLLER = "viewEventController";
	public static final String URL_VIEW_MY_EVENTS_URL = "viewMyEvents";
	
	public static final String URL_EDIT_EVENT = "event_edit.jsp";
	public static final String URL_EDIT_EVENT_CONTROLLER = "editEventController";
	public static final String URL_EDIT_EVENT_PAGE_CONTROLLER = "editEventPageController";

	public static String buildViewEventString(Long eventId) {
		return String.format("%s?%s=%d", EventUtils.URL_VIEW_EVENT_CONTROLLER,
		      EventUtils.PARAMETER_EVENT_ID, eventId);
	}
}
