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

import org.springframework.social.yammer.api.MessageInfo;
import org.springframework.social.yammer.api.MessageOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Morten Andersen-Gott
 *
 */
public class MessageTemplate extends AbstractYammerOperations implements MessageOperations {

	private RestTemplate restTemplate;
	
	/**
	 * @param restTemplate restTemplate
	 */
	public MessageTemplate(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}

	public MessageInfo getMessages(long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages.json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesFollowing(long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/following.json", params), MessageInfo.class);
	}
	
	public MessageInfo getMessagesSent(long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/sent.json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesPrivate(long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/private.json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesAboutTopic(long topicId, long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/about_topic/"+topicId+".json", params), MessageInfo.class);
	}
	
	public MessageInfo getMessagesInGroup(long groupId, long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/in_group/"+groupId+".json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesInThread(long threadId, long olderThan, long newerThan, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, null, limit);
		return restTemplate.getForObject(buildUri("messages/in_thread/"+threadId+".json", params), MessageInfo.class);
	}
	
	public MessageInfo getMessagesFromUser(long userId, long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/from_user/"+userId+".json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesLikedByUser(long userId, long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/liked_by/"+userId+".json", params), MessageInfo.class);
	}

	public MessageInfo getMessagesReceived(long olderThan, long newerThan, String threaded, int limit){
		MultiValueMap<String, String> params = buildParams(olderThan, newerThan, threaded, limit);
		return restTemplate.getForObject(buildUri("messages/received.json", params), MessageInfo.class);
	}
	
	public MessageInfo postUpdate(String message){
		return postUpdate(message, new YammerPostDetails());
	}

	public MessageInfo postUpdate(String message, YammerPostDetails details){
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("body", message);
		params.putAll(details.toParameters());
		return restTemplate.postForObject(buildUri("messages.json"),params ,MessageInfo.class);
	}
	
	private MultiValueMap<String, String> buildParams(long olderThan, long newerThan, String threaded, int limit) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		if(olderThan!=0){
			params.set("older_than", String.valueOf(olderThan));
		}
		if(newerThan!=0){
			params.set("newer_than", String.valueOf(newerThan));
		}
		if(threaded!=null){
			params.set("threaded", String.valueOf(threaded));
		}
		if(limit!=0){
			params.set("limit", String.valueOf(limit));
		}
		return params;
	}

	public void like(long messageId) {
		restTemplate.postForObject(buildUri("messages/liked_by/current.json", "message_id", String.valueOf(messageId)),null, String.class);
	}

	public void unlike(long messageId) {
		restTemplate.delete(buildUri("messages/liked_by/current.json", "message_id", String.valueOf(messageId)));		
	}

	public void delete(long messageId) {
		restTemplate.delete(buildUri("messages/"+messageId)); //400 in case message does not exist
	}

}
