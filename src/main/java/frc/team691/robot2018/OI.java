package frc.team691.robot2018;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final double STICK_DEADBAND = 0.20;

    public Joystick stick;
    public Joystick astick;

    public OI() {
        stick = new Joystick(0);
        astick = new Joystick(1);
    }

    public Joystick getStick() {
        return stick;
    }

    public Joystick getAStick() {
    	return astick;
    }
}
