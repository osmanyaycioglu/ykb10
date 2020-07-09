package com.training.ykb.notification;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public boolean notifyWithSMS(final String customerName,
                                 final String customerNumber) {
        return true;
    }

    public boolean notifyWithEMAIL(final String customerName,
                                   final String customerEmail) {
        return true;
    }
}
