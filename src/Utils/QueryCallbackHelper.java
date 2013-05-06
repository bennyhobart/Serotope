package Utils;
import java.util.ArrayList;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;


public class QueryCallbackHelper implements QueryCallback {
	ArrayList<Fixture> fixtures;
	QueryCallbackHelper() {
		fixtures = new ArrayList<Fixture>();
	}
	@Override
	public boolean reportFixture(Fixture fixture) {
		fixtures.add(fixture);
		return true;
	}

}
