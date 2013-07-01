package bg.su.fmi.st.calendar.servlet.eventinvitations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;

public class ViewEventInvitationsTableServlet extends HttpServlet {

    private static final long serialVersionUID = 5289774875930198329L;
    
    // columns ? - some event info, two buttons for accept/reject
    // update the response as well, if no -add comment

    @EJB
    protected EventInvitationDAO eventInvitationsDAO;
    protected HttpServletRequest request;

    public List<EventInvitation> getEventInvitationsForDisplay() {
        List<EventInvitation> allEventInvitations = eventInvitationsDAO.getAllEventInvitations();
        String currentUsername = this.request.getRemoteUser();

        List<EventInvitation> myEventInvitations = new ArrayList<EventInvitation>();
        for(EventInvitation e: allEventInvitations){
            if(e.getInvitedUser().getUsername().equals(currentUsername)){
            	myEventInvitations.add(e);
            }
        }
        return myEventInvitations;
    }


    public String getTitle() {
        return "Invitations";
    }
}
