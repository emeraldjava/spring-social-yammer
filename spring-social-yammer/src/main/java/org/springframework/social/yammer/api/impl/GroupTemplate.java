/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.yammer.api.impl;

import org.springframework.social.yammer.api.Group;
import org.springframework.social.yammer.api.GroupMembers;
import org.springframework.social.yammer.api.GroupOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Morten Andersen-Gott
 *
 */
public class GroupTemplate extends AbstractYammerOperations implements GroupOperations {

	private RestTemplate restTemplate;

	public GroupTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Group> getGroups(int page, Character letter, String sortBy, boolean reverse) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("page", String.valueOf(page));
		if(sortBy!=null){
			params.set("sort_by", sortBy);
		}
		params.set("reverse", String.valueOf(reverse));
		if(letter!=null){
			params.set("letter", String.valueOf(letter));
		}
		return restTemplate.getForObject(buildUri("groups.json", params), GroupList.class);
	}

	public Group getGroup(long groupId) {
		return restTemplate.getForObject(buildUri("groups/"+String.valueOf(groupId)+".json"), Group.class);
	}

	public GroupMembers getGroupMembers(long groupId) {
		return restTemplate.getForObject(buildUri("groups/"+String.valueOf(groupId)+"/members.json"), GroupMembers.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.yammer.api.GroupOperations#createGroup(java.lang.String, boolean)
	 */
	public void createGroup(String name, boolean isPrivate) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("name",name);
		params.set("private", String.valueOf(isPrivate));
		restTemplate.postForEntity(buildUri("groups"), params, String.class);
	}

	/**
	 * Method returns 401 from Yammer, so it isn't visible in GroupOperations yet
	 * @param groupId groupId
	 * @param name name
	 * @param isPrivate isPrivate
	 */
	public void updateGroup(long groupId, String name, boolean isPrivate) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("name",name);
		params.set("private", String.valueOf(isPrivate));
		restTemplate.put(buildUri("groups/"+groupId), params);
	}
	
	public void joinGroup(long groupId){
		restTemplate.postForObject(buildUri("group_memberships.json", "group_id", String.valueOf(groupId)), null, String.class);
	}
	
	public void leaveGroup(long groupId){
		restTemplate.delete(buildUri("group_memberships.json", "group_id", String.valueOf(groupId)));
	}
}
