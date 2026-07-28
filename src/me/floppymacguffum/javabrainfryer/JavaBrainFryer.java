/*
	MIT License

	Copyright (c) 2026 FloppyMacguffum

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
 */
package me.floppymacguffum.javabrainfryer;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class JavaBrainFryer
{
	private byte[] memory;
	private int mp;

	public JavaBrainFryer(int memorySize) {
		this.memory = new byte[memorySize];
		this.mp = 0;
	}

	public JavaBrainFryer() {
		this(30000);
	}

	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("javabrainfryer.jar program.b");
			System.exit(1);
		}
		FileInputStream fis = new FileInputStream(args[0]);
		DataInputStream dis = new DataInputStream(fis);
		byte[] fileIn = new byte[fis.available()];
		dis.readFully(fileIn);
		dis.close();
		fis.close();
		new JavaBrainFryer().eval(new String(fileIn));
	}

	public void eval(String code) {
		for(int iptr = 0; iptr < code.length(); iptr++) {
			switch (code.charAt(iptr)) {
				case 43:
					memory[mp]++;
					break;
				case 44:
					try {
						memory[mp] = (byte) (System.in.read() & 0xff);
					} catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 45:
					memory[mp]--;
					break;
				case 46:
					try {
						System.out.write(new byte[] {(byte) memory[mp]});
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 60:
					mp--;
					if(isAddressOutOfMemoryBounds(mp)) mp = memory.length - 1;
					break;
				case 62:
					mp++;
					if(isAddressOutOfMemoryBounds(mp)) mp = 0;
					break;
				case 91:
					if(memory[mp] == 0) {
						int loop = 1;
						while(loop > 0) {
							iptr++;
							if(code.charAt(iptr) == '[') {
								loop++;
							}
							if(code.charAt(iptr) == ']') {
								loop--;
							}
						}
					}
					break;
				case 93:
					int loop = 1;
					while(loop > 0) {
						iptr--;
						if(code.charAt(iptr) == '[') {
							loop--;
						}
						if(code.charAt(iptr) == ']') {
							loop++;
						}
					}
					iptr--;
					break;
				default:
					break;
			}
		}
	}

	private boolean isAddressOutOfMemoryBounds(int addr) {
		return addr > memory.length - 1 || 0 > addr;
	}
}