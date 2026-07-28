# JavaBrainFryer
A KISS Brainfuck interpreter written in Java.
# Features
1. Tries to follow the principles of keep it simple stupid
2. Tape/Cell/Memory array size by default is 30000 bytes
3. Can be used as a library
# Building and running
Run the following to build
```
./buildjar.sh
```
...and to run the Jar.
```
java -jar javabrainfryer.jar <program.b>
```
Cleanup the build with
```
./cleanup.sh
```
# Using as a library
Obviously the first thing you'll need to do is simply add `javabrainfryer.jar` to your class path.
Here's some example code for using JavaBrainFryer as a library.
```java
import me.floppymacguffum.javabrainfryer.JavaBrainFryer;

public class Example
{
	public static void main(String[] args)
	{
		JavaBrainFryer jbf = new JavaBrainFryer(30000);
		// or...
		//JavaBrainFryer jbf = new JavaBrainFryer();// The default is 30000 bytes.
		jbf.eval(".+[.+]");
	}
}
```
