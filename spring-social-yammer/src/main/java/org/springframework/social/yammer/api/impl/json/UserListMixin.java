package org.springframework.social.yammer.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.yammer.api.YammerProfile;

import java.util.List;

/**
 * Created by pauloconnell on 22/06/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class UserListMixin {

    @JsonCreator
    public UserListMixin(
            @JsonProperty("users") List<YammerProfile> users,
            @JsonProperty("more_available") boolean moreAvailable
    ) {}
}
