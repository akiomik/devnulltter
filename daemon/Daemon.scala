package devnulltter

import java.io._
import com.twitter.util._
import devnulltter._

object Daemon {
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

        // Open named pipe
        var reader = try {
            new DeviceReader(conf.dev)
        }
        catch {
            case e: Exception => {
                println("Failed to open " + conf.dev + ".")
                sys.exit(1)
            }
        }

        // create twitter instance
        val consumer = conf.consumer
        val token = conf.token
        val twitter = try {
            new Twitter(consumer, token)
        }
        catch {
            case e: Exception => {
                println("Failed to create twitter instance.")
                e.printStackTrace
                reader.close
                sys.exit(1)
            }
        }

        // continue to try reading tweet from file, and post it
        var continue = true
        while (continue) {
            val tweet = reader.read

            if (!(tweet == null || tweet.isEmpty || tweet.length > 140)) {
                try {
                    tweet match {
                        case "exit" => continue = false
                        case _      => twitter.post(tweet)
                    }
                }
                catch {
                    // e > /dev/null
                    case e: Exception => {}
                }
            }
        }

        reader.close
    }
}
