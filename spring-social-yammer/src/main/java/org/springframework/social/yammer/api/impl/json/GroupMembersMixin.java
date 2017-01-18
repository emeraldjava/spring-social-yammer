package org.springframework.social.yammer.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.yammer.api.Group;
import org.springframework.social.yammer.api.YammerReference;

import java.util.List;

/**
 * Created by pauloconnell on 17/01/17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
abstract class GroupMembersMixin {

    @JsonCreator
    public GroupMembersMixin(
            @JsonProperty("group") Group group,
            @JsonProperty("users") List<YammerReference> users
    ) {}
}
