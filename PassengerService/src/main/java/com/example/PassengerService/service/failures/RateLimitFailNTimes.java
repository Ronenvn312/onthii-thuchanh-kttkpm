package com.example.PassengerService.service.failures;

import com.example.PassengerService.exception.RateLimitExceededException;

public class RateLimitFailNTimes implements PotentialFailure {
    int times;
    int failedCount;

    public RateLimitFailNTimes(int times) {
        this.times = times;
    }

    @Override
    public void occur() {
        if (failedCount++ < times) {
            System.out.println("Rate limit exceeded");
            throw new RateLimitExceededException("Rate limit exceeded, try again in some time", "RL-101");
        }
    }
}
