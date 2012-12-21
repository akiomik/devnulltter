package devnulltter

import twitter4j._
import twitter4j.auth._
import devnulltter._

class Twitter(consumer: Consumer, token: Token) {
    val factory = new TwitterFactory();
    val twitter = factory.getInstance();
    val accessToken = new AccessToken(token.token, token.secret)

    twitter.setOAuthConsumer(consumer.key, consumer.secret)
    twitter.setOAuthAccessToken(accessToken)

    // tweetを投稿
    def post(tweet: String) {
        twitter.updateStatus(tweet)
    }
}
