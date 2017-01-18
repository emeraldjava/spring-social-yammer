package org.springframework.social.yammer.api;

import java.util.List;

/**
 * Created by pauloconnell on 17/01/17.
 */
public class GroupMembers {

    private Group group;
    private List<YammerReference> users;

    public GroupMembers(Group group, List<YammerReference> users) {
        this.group = group;
        this.users = users;
    }

    public Group getGroup() {
        return group;
    }

    public List<YammerReference> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupMembers{");
        sb.append("group=").append(group);
        sb.append(", users=").append(getUsers());
        sb.append('}');
        return sb.toString();
    }
}
