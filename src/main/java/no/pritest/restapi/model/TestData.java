/**
    This file is part of Pritest.

    Pritest is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Pritest is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package no.pritest.restapi.model;

public class TestData implements Comparable<TestData> {
	private String className;
	private int fails;
	
	public TestData(String className, int fails) {
		this.className = className;
		this.fails = fails;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String name) {
		this.className = name;
	}
	
	public int getFails() {
		return fails;
	}
	public void setFails(int fails) {
		this.fails = fails;
	}

	public int compareTo(TestData arg) {
		return this.fails - arg.getFails();
	}
}
