package com.assignment3.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class AppointmentController {

    @MessageMapping("/sender")
    @SendTo("/topic/receiver")
    public AppointmentNotificationReceiver appointmentReceived(AppointmentNotificationSender appointmentSent) throws Exception{
        Thread.sleep(1000);
        return new AppointmentNotificationReceiver("Appointment created for patient:  "
                + HtmlUtils.htmlEscape(appointmentSent.getPatientName())
                + " on: " + HtmlUtils.htmlEscape((appointmentSent.getDateOfConsultation().toString())) );
    }
}
