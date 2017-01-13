package org.springframework.social.yammer.api;

public interface Yammer{

	/**
	 * Returns the portion of the API containing the Thread operations
	 * @return ThreadOperations ThreadOperations
	 */
	ThreadOperations threadOperations();

	/**
	 * Returns the portion of the API containing the Subscription (follow) operations
	 * @return SubscriptionOperations SubscriptionOperations
	 */
	SubscriptionOperations subscriptionOperations();

	/**
	 * Returns the portion of the API containing the Topic (tags) operations
	 * @return TopicOperations TopicOperations
	 */
	TopicOperations topicOperations();

	/**
	 * Returns the portion of the API containing the Search operations
	 * @return SearchOperations SearchOperations
	 */
	SearchOperations searchOperations();

	/**
	 * Returns the portion of the API containing the Group operations
	 * @return GroupOperations GroupOperations
	 */
	GroupOperations groupOperations();

	/**
	 * Returns the portion of the API containing the Message operations
	 * @return MessageOperations MessageOperations
	 */
	MessageOperations messageOperations();

	/**
	 * Returns the portion of the API containing the User operations
	 * @return UserOperations UserOperations
	 */
	UserOperations userOperations();

}
