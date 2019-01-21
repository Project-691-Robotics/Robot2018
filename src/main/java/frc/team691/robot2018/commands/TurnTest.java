package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.subsystems.Drivetrain;

public class TurnTest extends Command {
    private static final int DEFAULT_ANGLE = 90;
    
	private Drivetrain dt = Robot.drivetrain;
	private int angle;
    private boolean isDone = false;
    
    public TurnTest() {
    	this(DEFAULT_ANGLE);
    }
    
    public TurnTest(int angle) {
    	requires(dt);
    	setAngle(angle);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("TT init --TO--: " + (dt.navx.getAngle() + angle));
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//System.out.println("MMT exec");
    	isDone = dt.turnAngle(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println("MMT fin");
        return isDone;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("TT end");
    	dt.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("TT interrupt");
    	end();
    }
    
    public void setAngle(int a) {
    	angle = a;
    }
}
