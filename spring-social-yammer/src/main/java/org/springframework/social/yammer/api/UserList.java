package org.springframework.social.yammer.api;

import java.util.List;

/**
 * Created by pauloconnell on 22/06/17.
 */
public class UserList {

    private List<YammerReference> users;
    private boolean moreAvailable;

    public UserList(List<YammerReference> users,boolean moreAvailable) {
        this.users=users;
        this.moreAvailable=moreAvailable;
    }

    public boolean getMoreAvailable() {
        return moreAvailable;
    }

    public List<YammerReference> getUsers() {
        return users;
    }

}
