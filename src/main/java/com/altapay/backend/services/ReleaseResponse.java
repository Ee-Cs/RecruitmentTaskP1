package com.altapay.backend.services;

import com.altapay.util.DummyDataHelper;

public class ReleaseResponse {
    boolean successful = true;

    public boolean wasSuccessful() {
        DummyDataHelper.dummyCheck(successful);
        return successful;
    }
}
