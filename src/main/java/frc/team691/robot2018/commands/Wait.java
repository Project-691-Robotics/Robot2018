package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
	private static final int DEFAULT_WAIT_SEC = 10;
    
    private Timer timer;
    private int waitSec;
    
    public Wait() {
    	this(DEFAULT_WAIT_SEC);
    }
    
    public Wait(int ws) {
    	timer = new Timer();
    	setWaitSec(ws);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	System.out.println("Wait init");
    	System.out.println(timer.get());
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//System.out.println("Autonomous execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println(timer.get());
    	return timer.get() > waitSec;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("Wait end");
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("Wait interrupted");
    	end();
    }
    
    public void setWaitSec(int ws) {
    	waitSec = ws;
    }
}
