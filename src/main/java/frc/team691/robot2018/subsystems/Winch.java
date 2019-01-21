package frc.team691.robot2018.subsystems;

import frc.team691.robot2018.RobotMap;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
    private static final double MOTOR_DRIVE = 0.84;
    
    private final SpeedControllerGroup motorGroup = RobotMap.winchMotorGroup;

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
