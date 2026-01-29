package com.altapay.backend.services;

import com.altapay.util.DummyDataHelper;

public class CaptureResponse {
    boolean successful = true;

    public boolean wasSuccessful() {
        DummyDataHelper.dummyCheck(successful);
        return successful;
    }
}
