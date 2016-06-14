package com.edx.shell.android.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

public class CustomTwitterClientAPI extends TwitterApiClient {

    public CustomTwitterClientAPI(Session session) {
        super(session);
    }

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
