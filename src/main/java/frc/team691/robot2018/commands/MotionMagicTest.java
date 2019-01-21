package frc.team691.robot2018.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team691.robot2018.Robot;
import frc.team691.robot2018.subsystems.Drivetrain;

public class MotionMagicTest extends Command {
	private static final int DEFAULT_DIST_IN = 240;
	private static final int DEFAULT_MM_CRUISE_VEL = 1800;
    private static final int DEFAULT_MM_ACCEL = 500;
    
	private Drivetrain dt = Robot.drivetrain;
	private int mmCruiseVel = DEFAULT_MM_CRUISE_VEL;
	private int mmAccel = DEFAULT_MM_ACCEL;
	private int distEnc;
    private boolean isDone = false;
    
    public MotionMagicTest() {
    	this(DEFAULT_DIST_IN);
    }
    
    public MotionMagicTest(int distIn) {
    	requires(dt);
    	setDistInches(distIn);
    }
    
    public MotionMagicTest(int distIn, int mmcv, int mma) {
    	this(distIn);
    	mmCruiseVel = mmcv;
    	mmAccel = mma;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("MMT init --TO--: " + distEnc);
    	// TODO: Check if need reset
    	dt.resetEncoders();
    	dt.configMotionMagic(mmCruiseVel, mmAccel);
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//System.out.println("MMT exec");
    	isDone = dt.driveMotionMagic(distEnc);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//System.out.println("MMT fin");
        return dt.getEncoders() > 50 && isDone;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("MMT end: " + dt.getEncoders());
    	dt.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("MMT interrupt");
    	end();
    }
    
    public void setDistInches(double in) {
    	distEnc = (int) (in * 1000 / 18);
    }
}
