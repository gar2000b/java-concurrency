package localvobjectstate;

public class SharedObject {

	int someState = 5;

	public int getSomeState() {
		return someState;
	}

	public void setSomeState(int someState) {

		this.someState = someState;

		int someInternalState = someState;
		NestedObject nestedObject = new NestedObject();
		nestedObject.setNestedState(someState);

		System.out.println("* Internal state set to: " + someInternalState);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("* internal state is now " + someInternalState);
		System.out.println("** object instance state is now " + getSomeState()
				+ " - this may be difference from internal and nested states as expected");
		System.out.println("* nested state is now " + nestedObject.getNestedState());
	}
}
