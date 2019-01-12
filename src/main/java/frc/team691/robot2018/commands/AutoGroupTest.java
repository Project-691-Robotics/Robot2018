package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGroupTest extends CommandGroup {
	private static final int TT_ANGLE = 90;
	private static final int SIDE_DIST_IN = 200;
	private static final int MID_DIST_IN = 180;
	private static final int SWITCH_DIST_IN = 24;
	//private static final int SWITCH_DIST_IN = 240;
	private static final int SWITCH_TIMEOUT_SEC = 6;
	
	MotionMagicTest mmt = new MotionMagicTest();
	TurnTest tt = new TurnTest();
	Autonomous ad = new Autonomous(SWITCH_DIST_IN);
	ElevatorUp eu = new ElevatorUp();
	IntakeOut io = new IntakeOut();

	public AutoGroupTest() {
		addSequential(mmt);
		//addSequential(new Wait(2));//--
		addSequential(tt);
		addSequential(eu);
		//addSequential(new Wait(2));//--
		addSequential(ad, SWITCH_TIMEOUT_SEC);
		//--addParallel(ad, SWITCH_TIMEOUT_SEC);
		addSequential(io);
	}
	
	public void setParams(double distIn, int angle) {
		mmt.setDistInches(distIn);
		tt.setAngle(angle);
	}
	
	public void setSwitchParams(int adDist, int euDurSec, int ioDurSec) {
		ad.setDistInches(adDist);
		eu.setDurSec(euDurSec);
		io.setDurSec(ioDurSec);
	}

	public void setColorSpot(int color, int spot) {
		/*if (spot == 0) {
			setParams(MID_DIST_IN, 0);
			setSwitchParams(0, 0, 0);
		} else {
			setParams(SIDE_DIST_IN, (spot == color ? spot * TT_ANGLE : 0));
		}*/
		if (spot == color) {
			setParams(SIDE_DIST_IN, spot * TT_ANGLE);
		} else {
			setParams((spot == 0 ? MID_DIST_IN : SIDE_DIST_IN), 0);
			setSwitchParams(0, 0, 0);
		}
	}
}
