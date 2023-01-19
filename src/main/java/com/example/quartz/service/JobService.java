package com.example.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobService implements SampleJobService {
    @Override
    public void sendEmail(String email, String message) {
        //TODO request for messages to send & instant send
        log.info("Sending email");
    }

    @Override
    public void sendTgMessage(String message) {
        log.info("Sending Telegram message");
    }
}
