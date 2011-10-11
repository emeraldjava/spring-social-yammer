package org.springframework.social.yammer.api.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.social.yammer.api.UserInfo;
import org.springframework.social.yammer.api.UserOperations;
import org.springframework.social.yammer.api.YammerProfile;
import org.springframework.util.StringUtils;

public class UserTemplateTest extends AbstractYammerApiTest{
	
	@Test
	public void testGetUserInfoById(){
        Long id = 4022983L;
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users/"+id+".json"))
				.andExpect(method(GET))
				.andRespond(withResponse(new ClassPathResource("yammer-user.json", getClass()), responseHeaders));
		YammerProfile yProfile = yammerTemplate.userOperations().getUser(id);
		assertThat(yProfile.getId(), equalTo(id));
		assertYammerProfile(yProfile);
	}

    @Test
	public void testGetCurrentUserProfile(){
        Long currentUserId = 4022983L;
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users/current.json"))
				.andExpect(method(GET))
				.andRespond(withResponse(new ClassPathResource("yammer-user.json", getClass()), responseHeaders));
		YammerProfile yProfile = yammerTemplate.userOperations().getCurrentUser();
		assertThat(yProfile.getId(), equalTo(currentUserId));
		assertYammerProfile(yProfile);
	}

	@Test
	public void testGetUserInfoByEmail() throws UnsupportedEncodingException{
		String email = "ilya@users.yammer.com";
		String encodedEmail = StringUtils.replace(email, "@", "%40");
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users/by_email.json?email="+encodedEmail))
		.andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("yammer-users.json", getClass()), responseHeaders));
		YammerProfile yProfile = yammerTemplate.userOperations().getUserByEmail(email);
		assertYammerProfile(yProfile);
	}
	
	@Test
	public void testGetUsers() throws UnsupportedEncodingException{
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users.json?page=1&sort_by=messages&reverse=false&letter=A"))
		.andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("yammer-users.json", getClass()), responseHeaders));
		List<YammerProfile> users = yammerTemplate.userOperations().getUsers(1, UserOperations.SORT_BY_MESSAGES, false, 'A');
		assertYammerProfile(users.get(0));
	}
	@Test
	public void testGetUsers_page() throws UnsupportedEncodingException{
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users.json?page=1&reverse=false"))
		.andExpect(method(GET))
		.andRespond(withResponse(new ClassPathResource("yammer-users.json", getClass()), responseHeaders));
		List<YammerProfile> users = yammerTemplate.userOperations().getUsers(1);
		assertYammerProfile(users.get(0));
	}
	
	@Test
	public void testUpdateUser(){
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockServer.expect(requestTo("https://www.yammer.com/api/v1/users/123456.json?full_name=Dilbert+Gradle&job_title=Developer&location=The+Streaming&im_provider=gtalk&im_username=dilbert.gradle%40gmail.com&work_telephone=123456&external_profiles=http%3A%2F%2Fwww.linkedin.com%2Fdilbertgradle&work_extension=1&mobile_telephone=121212&significant_other=Ms+Gradle&kids_names=Dilbert+Gradle+jr&interests=Building&summary=Featured+in+The+Streaming&expertise=Awsomenes&education%5B%5D=foo%2Cbar%2Cfoobar%2C2000%2C2001&education%5B%5D=bar%2Cfoo%2Cbarfoo%2C2001%2C&previous_companies%5B%5D=foobar+consulting%2Cconsultant%2Cconsulting+on+foobarnesse%2C2001%2C&previous_companies%5B%5D=yabadoo%2Ccaveman%2Ccavingness%2C1900%2C1950"))
		.andExpect(method(PUT))
		.andRespond(withResponse("", responseHeaders));	
		UserInfo userInfo = new UserInfo(null, "Dilbert Gradle", "Developer", "The Streaming", "gtalk", "dilbert.gradle@gmail.com", "123456", "1", "121212", "http://www.linkedin.com/dilbertgradle", "Ms Gradle","Dilbert Gradle jr", "Building", "Featured in The Streaming", "Awsomenes");
		userInfo.addEducation("foo", "bar", "foobar", 2000, 2001);
		userInfo.addEducation("bar", "foo", "barfoo", 2001, null);
		userInfo.addExperience("foobar consulting", "consultant", "consulting on foobarnesse", 2001,null);
		userInfo.addExperience("yabadoo", "caveman", "cavingness", 1900,1950);
		yammerTemplate.userOperations().updateProfile(123456L, userInfo);
	}

	private void assertYammerProfile(YammerProfile yProfile) {
		assertThat(yProfile.getStats(), notNullValue());
		assertThat(yProfile.getStats().getFollowers(), equalTo(1L));
		assertThat(yProfile.getStats().getFollowing(), equalTo(1L));
		assertThat(yProfile.getStats().getUpdates(), equalTo(1L));
		assertThat(yProfile.getMugshotUrl(), notNullValue());
		assertThat(yProfile.getExpertise(), equalTo("Socializing"));
		assertThat(yProfile.getSummary(), equalTo("A summary"));
		assertThat(yProfile.getType(), notNullValue());
		assertThat(yProfile.isAdmin(), is(true));
		assertThat(yProfile.getFullName(), notNullValue());
		assertThat(yProfile.getName(), notNullValue());
		assertThat(yProfile.getUrl(), notNullValue());
		assertThat(yProfile.getSchools(), notNullValue());
		assertThat(yProfile.getSchools().isEmpty(), is(false));
		assertThat(yProfile.getExternalUrls(), notNullValue());
		assertThat(yProfile.getExternalUrls().isEmpty(), is(false));
		assertThat(yProfile.getContact(), notNullValue());
		assertThat(yProfile.getContact(), notNullValue());
		assertThat(yProfile.getJobTitle(), equalTo("Social animal"));
	}
	
	
}
