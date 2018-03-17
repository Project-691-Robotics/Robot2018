package org.usfirst.frc691.Robot2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGroupTest extends CommandGroup {
	MotionMagicTest mmt = new MotionMagicTest();
	TurnTest tt = new TurnTest();

	public AutoGroupTest() {
		addSequential(mmt);
		addSequential(tt);
	}
	
	public void setParams(double distIn, int angle) {
		mmt.setDistInches(distIn);
		tt.setAngle(angle);
	}
}
