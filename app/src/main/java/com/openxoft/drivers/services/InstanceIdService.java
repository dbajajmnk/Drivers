package com.openxoft.drivers.services;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by openxoft on 19/05/17.
 */

public class InstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
