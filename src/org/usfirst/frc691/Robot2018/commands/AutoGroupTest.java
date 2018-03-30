package org.usfirst.frc691.Robot2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGroupTest extends CommandGroup {
	private static final int TT_ANGLE = 90;
	private static final int SIDE_DIST_IN = 200;
	private static final int MID_DIST_IN = 180;
	
	MotionMagicTest mmt = new MotionMagicTest();
	TurnTest tt = new TurnTest();
	ElevatorUp eu = new ElevatorUp();
	IntakeOut io = new IntakeOut();

	public AutoGroupTest() {
		addSequential(mmt);
		addSequential(tt);
		addSequential(eu);
		addSequential(io);
	}
	
	public void setParams(double distIn, int angle) {
		mmt.setDistInches(distIn);
		tt.setAngle(angle);
	}
	
	public void setSwitchParams(int euDurSec, int ioDurSec) {
		eu.setDurSec(euDurSec);
		io.setDurSec(ioDurSec);
	}

	public void setColorSpot(int color, int spot) {
		if (spot == 0) {
			setParams(MID_DIST_IN, 0);
		} else {
			setParams(SIDE_DIST_IN, (spot == color ? spot * TT_ANGLE : 0));
			setSwitchParams(0, 0);
		}
	}
}
