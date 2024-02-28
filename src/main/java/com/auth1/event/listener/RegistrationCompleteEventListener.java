package com.auth1.event.listener;

import com.auth1.User.User;
import com.auth1.User.UserServices;
import com.auth1.event.RegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserServices userServices;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {


        //1.Get the newly registered user

        User theUser = event.getUser();
        //2.create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //3.Save the verification token for the user
        userServices.saveUserVerificationToken(theUser,verificationToken);

        //4.Build the verification url to be sent to the user
        String url=event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
        //5.Send the email.
        log.info("click the link to verify your registration : {}",url);
    }
}
