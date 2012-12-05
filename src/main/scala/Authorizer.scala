package devnulltter

import java.io._
import twitter4j._
import twitter4j.auth._
import com.twitter.util._
import devnulltter._

object Authorizer {
    def main(args: Array[String]) {
        // Read config file
        val confpath = "config/config.scala"
        val conf = try {
            Eval[Config](new File(confpath))
        }
        catch {
            case e: Exception => {
                println("Failed to open " + confpath + ".")
                sys.exit(1)
            }
        }

        // set consumer key
        val consumer = conf.consumer
        val twitter = new TwitterFactory().getInstance()
        twitter.setOAuthConsumer(consumer.key, consumer.secret)

        // print authorization url
        val requestToken = twitter.getOAuthRequestToken()
        println("Open the following URL and grant access to your account:")
        println(requestToken.getAuthorizationURL())

        // get access token
        val pin = readLine("Enter the PIN(if aviailable) or just hit enter.[PIN]:")
        val accessToken = try {
            if(pin.length() > 0)
                twitter.getOAuthAccessToken(requestToken, pin)
            else
                twitter.getOAuthAccessToken()
        }
        catch {
            case te: TwitterException => {
                if (te.getStatusCode() == 401)
                    println("Unable to get the access token.")
                else
                    te.printStackTrace()
            }
            sys.exit(1)
        }

        println("Write your access token in 'config.scala'")
        println("access token : " + accessToken.getToken())
        println("access token secret : " + accessToken.getTokenSecret())
    }
}


