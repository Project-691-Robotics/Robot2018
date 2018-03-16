// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc691.Robot2018.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc691.Robot2018.Robot;
import org.usfirst.frc691.Robot2018.subsystems.Drivetrain;

public class Autonomous extends Command {
	private static final int DEFAULT_DIST = 1000;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private Drivetrain dt;
	private int distEnc = DEFAULT_DIST;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Autonomous() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	dt = Robot.drivetrain;
    	requires(dt);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("Autonomous init");
    	dt.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	System.out.println("Autonomous execute");
    	dt.driveAuto(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	System.out.println("Autonomous isFinished");
        //return dt.getEncoders() >= distEnc;
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("Autonomous end");
    	dt.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("Autonomous interrupted");
    }
    
    public void setDistInches(double in) {
    	distEnc = (int) (in * 1000 / 18);
    }
}
