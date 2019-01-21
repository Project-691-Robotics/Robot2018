package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.subsystems.*;

public class StickDrive extends Command {
	private static final int BUTTON_SWAP = 11;
	private static final int BUTTON_TWIST = 9;
	private static final int BUTTON_ELEV_UP = 3;
	private static final int BUTTON_ELEV_DN = 2;
	private static final int BUTTON_WINCH_UP = 4;
	private static final int BUTTON_WINCH_DN = 5;
	//private static int BUTTON_ELEV_INV = 12;

	private Joystick stick = Robot.oi.getStick();
	private Joystick astick = Robot.oi.getAStick();
	private Drivetrain dt = Robot.drivetrain;
	private Winch winch = Robot.winch;
	private Intake intake = Robot.intake;
	private Elevator elev = Robot.elevator;
	
	private int swap = 1;
	private boolean twist = false;
	private int elevInv = 1;

    public StickDrive() {
    	requires(dt);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("Sdrive init");
    	swap = 1;
    	twist = false;
    	elevInv = 1;
    	// TODO: Check if need reset
    	dt.resetEncoders();
    	
    	SmartDashboard.putNumber("swap", swap);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (stick.getRawButtonPressed(BUTTON_SWAP)) {
    		swap = -swap;
    		SmartDashboard.putNumber("swap", swap);
    	}
    	if (stick.getRawButtonPressed(BUTTON_TWIST)) {
    		twist = !twist;
    		SmartDashboard.putBoolean("twist", twist);
    	}
    	// Drivetrain
    	dt.driveArcade(-swap * stick.getY(), (twist ? stick.getZ() : stick.getX()));
    	// Intake
    	if (stick.getTrigger()) {
			intake.driveDir(-1);
    	} else if (stick.getRawButton(2)) {
			intake.driveDir(1);
    	} else {
			intake.driveStop();
		}
    	// Winch
    	if (astick.getRawButton(BUTTON_WINCH_UP)) {
			winch.driveDir(1);
    	} else if (astick.getRawButton(BUTTON_WINCH_DN)) {
			winch.driveDir(-1);
    	} else {
			winch.driveStop();
    	}
    	// Elevator
		/*
		if (stick.getRawButtonPressed(BUTTON_ELEV_INV)) {
			elevInv = -elevInv;
			SmartDashboard.putNumber("elevInv", elevInv);
		}
		*/
    	if (astick.getRawButton(BUTTON_ELEV_UP)) {
			elev.driveUp(elevInv);
    	} else if (astick.getRawButton(BUTTON_ELEV_DN)) {
			elev.driveDown(elevInv);
    	} else {
			elev.driveStop();
		}
		if (astick.getTrigger()) {
			elev.servoSetCatch();
		} else {
			elev.servoSetRelease();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("Sdrive end");
    	dt.driveStop();
    	winch.driveStop();
    	intake.driveStop();
    	elev.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("Sdrive interrupted");
    	end();
    }
}
