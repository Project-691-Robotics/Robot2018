package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.subsystems.Elevator;

public class ElevatorUp extends Command {
	private static final int DEFAULT_DUR_SEC = 3;
    
    private Elevator elev = Robot.elevator;
	private Timer timer;
    private int durSec;
    
    public ElevatorUp() {
    	this(DEFAULT_DUR_SEC);
    }
    
    public ElevatorUp(int ds) {
    	requires(elev);
    	timer = new Timer();
    	setDurSec(ds);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	System.out.println("EU init");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//System.out.println("Autonomous execute");
    	elev.driveDir(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println(timer.get());
    	return timer.get() > durSec;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("EU end");
    	timer.stop();
    	elev.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("EU interrupted");
    	end();
    }
    
    public void setDurSec(int ds) {
    	durSec = ds;
    }
}
