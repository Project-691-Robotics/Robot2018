// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc691.Robot2018.subsystems;

import org.usfirst.frc691.Robot2018.RobotMap;
import org.usfirst.frc691.Robot2018.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX leftTalon = RobotMap.drivetrainLeftTalon;
    private final WPI_TalonSRX rightTalon = RobotMap.drivetrainRightTalon;
    private final DifferentialDrive driver = RobotMap.drivetrainDriver;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new StickDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	SmartDashboard.putNumber("TL1", leftTalon.get());
    	SmartDashboard.putNumber("TR1", rightTalon.get());
    	SmartDashboard.putNumber("TLenc", leftTalon.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("TLspd", leftTalon.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("TRenc", rightTalon.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("TRspd", rightTalon.getSelectedSensorVelocity(0));
    }
    
    public void drive(double lspd, double rspd) {
    	leftTalon.set(lspd);
    	rightTalon.set(rspd);
    }
    
    public void driveArcade(double xspd, double zspd, boolean sqin) {
    	driver.arcadeDrive(xspd, zspd, sqin);
    }

    public void driveStick(Joystick stick) {
    	driveArcade(-stick.getY(), stick.getZ(), true);
    }
    
    public void driveStop() {
    	driver.stopMotor();
    }
    
    public void resetEncoders() {
    	RobotMap.drivetrainLeftTalon.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.drivetrainRightTalon.setSelectedSensorPosition(0, 0, 0);
    }
}
