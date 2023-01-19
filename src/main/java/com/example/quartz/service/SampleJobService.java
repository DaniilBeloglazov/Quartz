package com.example.quartz.service;

public interface SampleJobService {

    void sendEmail(String email, String message);

    void sendTgMessage(String message);
}
