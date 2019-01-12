package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMidGroup extends CommandGroup {
	private static final int SWITCH_DIST_IN = 160;
	private static final int SWITCH_TIMEOUT_SEC = 7;
	private static final int MM_CRUISE_VEL = 1200;
	private static final int MM_ACCEL = 500;
	
	MotionMagicTest mmt = new MotionMagicTest(SWITCH_DIST_IN, MM_CRUISE_VEL, MM_ACCEL);
	ElevatorUp eu = new ElevatorUp();
	IntakeOut io = new IntakeOut();

	public AutoMidGroup() {
		addSequential(mmt, SWITCH_TIMEOUT_SEC);
		addSequential(eu);
		addSequential(io);
	}
	
	public void setParams(double distIn) {
		mmt.setDistInches(distIn);
	}
	
	public void setSwitchParams(int euDurSec, int ioDurSec) {
		eu.setDurSec(euDurSec);
		io.setDurSec(ioDurSec);
	}

	public void setColorSpot(int color, int spot) {
		if (spot == color) {
			setSwitchParams(3, 2);
		} else {
			setSwitchParams(0, 0);
		}
	}
}
