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

import org.springframework.social.yammer.api.UserInfo;
import org.springframework.social.yammer.api.UserList;
import org.springframework.social.yammer.api.UserOperations;
import org.springframework.social.yammer.api.YammerProfile;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 
 * @author Morten Andersen-Gott
 *
 */
public class UserTemplate extends AbstractYammerOperations implements UserOperations{

	private RestTemplate restTemplate;
	
	/**
	 * @param restTemplate restTemplate
	 */
	public UserTemplate(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	
	public List<YammerProfile> getUsers(int page){
		return getUsers(page, null, false, null);
	}
	
	public List<YammerProfile> getUsers(int page, String sortBy, boolean reverse, Character letter){
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("page", String.valueOf(page));
		if(sortBy!=null){
			params.set("sort_by", sortBy);
		}
		params.set("reverse", String.valueOf(reverse));
		if(letter!=null){
			params.set("letter", String.valueOf(letter));
		}
		return restTemplate.getForObject(buildUri("users.json", params), YammerProfileList.class);
	}

	public YammerProfile getUser(long id){
        String userId = String.valueOf(id);
        return getUser(userId);
	}

    public YammerProfile getUser(String userId){
		URI uri = buildUri("users/"+userId+".json");
		return restTemplate.getForObject(uri, YammerProfile.class);
	}
	
	public void updateProfile(long userId, UserInfo userInfo){
		restTemplate.put(buildUri("users/"+String.valueOf(userId)+".json", userInfo.toParams()),null);
	}
	
	public YammerProfile getUserProfile(){
		return restTemplate.getForObject(buildUri("users/current.json"), YammerProfile.class);
	}
	
	public YammerProfile getUserByEmail(String email){
		URI uri = buildUri("users/by_email.json", "email", email);
		YammerProfileList profileList = restTemplate.getForObject(uri, YammerProfileList.class);

		//Yammer returns user inside a Json array element
		if(CollectionUtils.isEmpty(profileList)){
			return null;
		}
		return profileList.get(0);
	}

	/**
	 * https://developer.yammer.com/docs/usersliked_messagemessage_idjson
	 * @param messageId
	 * @return
	 */
	public List<YammerProfile> getUsersWhoLikedMessage(long messageId) {
		UserList userList = restTemplate.getForObject(buildUri("users/liked_message/" + messageId + ".json"), UserList.class);
		System.out.println(userList.getUsers());
		return userList.getUsers();
	}
}
