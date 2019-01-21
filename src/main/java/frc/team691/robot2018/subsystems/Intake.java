package frc.team691.robot2018.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team691.robot2018.RobotMap;

public class Intake extends Subsystem {
    private static final double MOTOR_DRIVE = 1.0;
    
    private final SpeedControllerGroup motorGroup = RobotMap.intakeMotorGroup;
    
    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(Robot.sdrive);
    }

    @Override
    public void periodic() {
    }
    
    public void drive(double spd) {
    	motorGroup.set(spd);
    }
    
    public void driveDir(int dir) {
    	drive(Math.signum(dir) * MOTOR_DRIVE);
    }
    
    public void driveStop() {
    	drive(0);
    }
}
