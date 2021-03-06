package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.subsystems.Drivetrain;

public class Autonomous extends Command {
	private static final int DEFAULT_DIST_IN = 240;
    //private static final int MIN_ENCODER_DIFF = 50;
    
	private Drivetrain dt = Robot.drivetrain;
	private int distEnc;
	private int goal;
	//private int lastEnc = 0;
    
    public Autonomous() {
    	this(DEFAULT_DIST_IN);
    }
    
    public Autonomous(int distIn) {
    	requires(dt);
    	setDistInches(distIn);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	goal = dt.getEncoders() + distEnc;
    	System.out.println("Autonomous init --TO--: " + goal);
    	//lastEnc = -2 * MIN_ENCODER_DIFF;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//System.out.println("Autonomous execute");
    	dt.driveAuto(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println("Autonomous isFinished");
    	int enc = dt.getEncoders();
    	//System.out.println(enc);
    	//int encDiff = enc - lastEnc;
    	//lastEnc = enc;
    	return enc >= goal;
        //return enc >= distEnc || encDiff < MIN_ENCODER_DIFF;
    	//return enc >= distEnc || (enc > 60 && !dt.navx.isMoving());
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("Autonomous end: " + dt.getEncoders());
    	dt.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("Autonomous interrupted");
    	end();
    }
    
    public void setDistInches(double in) {
    	distEnc = (int) (in * 1000 / 18);
    }
}
