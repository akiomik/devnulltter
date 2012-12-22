/dev/nulltter
===========

## Abstract
/dev/nulltter is a simple CLI twitter client.

## Usage
* update a status
  ```bash
$ echo 'hooray!' > /dev/nulltter
  ```

* shutdown the client
  ```bash
$ echo 'exit' > /dev/nulltter
  ```

## Repuirements
- java
- [sbt](http://scala-sbt.org/)
- [twitter application consumer key](https://dev.twitter.com/apps)

## Setup
1. Get the source code
  ```bash
$ git clone git://github.com/akiomik/devnulltter.git
$ cd devnulltter
  ```

2. Set your consumer key
  ```bash
$ vim config/config.scala.sample # write your consumer key
$ mv config/config.scala.sample config/config.scala
  ```

3. Compile the source code
  ```bash
$ sbt "project authorizer" assembly
$ sbt "project daemon" assembly
  ```

4. Set your access token
  ```bash
$ chmod 755 authorizer/target/authorizer.jar
$ java -jar authorizer/target/authorizer.jar  # get access token
$ vim config/config.scala           # and write it
  ```

5. Create the `/dev/nulltter` file
  ```bash
$ sudo mkfifo /dev/nulltter
  ```

  When you can't create `/dev/nulltter`, create it in another place.
  Then edit the path in `config/config.scala`.

6. Let's Tweet
   ```bash
$ chmod 755 daemon/target/devnulltterd.jar
$ java -jar daemon/target/devnulltterd.jar &
$ echo "/dev/nulltter now!" > /dev/nulltter
   ```

## Licence
Published under The NYSL, see NYSL.txt
