package frc.team691.robot2018.subsystems;

import frc.team691.robot2018.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	private static final double MOTOR_DRIVE_UP = 1.0;
	private static final double MOTOR_DRIVE_DN = 0.5;
	private static final double MOTOR_AUTO_DRIVE = 0.7;
	private static final double SERVO_RELEASE_POS = 1.0;
    private static final double SERVO_CATCH_POS = 0.43;
    
    private final SpeedControllerGroup motorGroup = RobotMap.elevatorMotorGroup;
    private final Servo servo = RobotMap.intakeServo;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    @Override
    public void periodic() {
    	SmartDashboard.putNumber("Servo", servo.get());
        SmartDashboard.putNumber("Servo(Angle)", servo.getAngle());
    }
    
    public void drive(double spd) {
    	motorGroup.set(spd);
    }
    
    public void driveDir(int dir) {
    	drive(Math.signum(dir) * MOTOR_AUTO_DRIVE);
    }
    
    public void driveUp(int dir) {
    	drive(dir * MOTOR_DRIVE_UP);
    }
    
    public void driveDown(int dir) {
    	drive(dir * -MOTOR_DRIVE_DN);
    }
    
    public void driveStop() {
    	drive(0);
    }
    
    public void setInverted(boolean i) {
    	motorGroup.setInverted(i);
    }
    
    public void servoSetRelease() {
    	servo.set(SERVO_RELEASE_POS);
    }
    
    public void servoSetCatch() {
    	servo.set(SERVO_CATCH_POS);
    }
}
