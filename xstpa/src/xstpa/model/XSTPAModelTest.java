package xstpa.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XSTPAModelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ProcessModelValueTest() {
		ProcessModelValue valueX = new ProcessModelValue();
		ProcessModelValue valueY = new ProcessModelValue();

		ProcessModelValue valueZ = new ProcessModelValue();
		valueZ.setController("A");
		valueZ.setValueText("A");
		valueZ.setPMV("A");
		
		valueY.setController(null);
		valueY.setValueText("B");
		valueY.setPMV("B");

		valueX.setController("Z");
		valueX.setValueText("Z");
		valueX.setPMV("Z");

		Assert.assertTrue(valueX.compareTo(valueY) > 0);
		Assert.assertTrue(valueY.compareTo(valueZ) < 0);
		
		Assert.assertTrue(valueZ.compareTo(valueX) < 0);
		Assert.assertTrue(valueZ.compareTo(valueY) > 0);
		
		valueY.setController(null);
		valueY.setValueText(null);
		valueY.setPMV(null);

		valueX.setController(null);
		valueX.setValueText(null);
		valueX.setPMV(null);

		Assert.assertTrue(valueX.compareTo(valueY) == 0);
		Assert.assertTrue(valueY.compareTo(valueX) == 0);
		
		valueY.setController(null);
		valueY.setValueText(null);
		valueY.setPMV("Y");

		valueX.setController(null);
		valueX.setValueText("X");
		valueX.setPMV(null);

		Assert.assertTrue(valueX.compareTo(valueY) == -valueY.compareTo(valueX));
	}

}
