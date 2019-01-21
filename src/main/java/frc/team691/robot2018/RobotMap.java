package frc.team691.robot2018;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static AHRS navx;
    public static WPI_TalonSRX drivetrainLeftTalon;
    public static WPI_TalonSRX drivetrainRightTalon;
    public static SpeedController intakeMotor1;
    public static SpeedController intakeMotor2;
    public static SpeedControllerGroup intakeMotorGroup;
    public static SpeedController winchMotor1;
    public static SpeedController winchMotor2;
    public static SpeedControllerGroup winchMotorGroup;
    public static SpeedController elevatorMotor1;
    public static SpeedController elevatorMotor2;
    public static SpeedControllerGroup elevatorMotorGroup;
    public static Servo intakeServo;
    public static WPI_TalonSRX drivetrainLeftSlaveTalon;
    public static WPI_TalonSRX drivetrainRightSlaveTalon;

    public static void init() {
    	navx = new AHRS(SPI.Port.kMXP);
        drivetrainLeftTalon = new WPI_TalonSRX(1);
        
        
        drivetrainRightTalon = new WPI_TalonSRX(0);
        
        
        intakeMotor1 = new Spark(0);
        intakeMotor1.setInverted(true);
        intakeMotor2 = new Spark(1);
        intakeMotor2.setInverted(false);
        intakeMotorGroup = new SpeedControllerGroup(intakeMotor1, intakeMotor2);
        
        winchMotor1 = new Spark(2);
        winchMotor1.setInverted(false);
        winchMotor2 = new Spark(3);
        winchMotor2.setInverted(false);
        winchMotorGroup = new SpeedControllerGroup(winchMotor1, winchMotor2);
        
        elevatorMotor1 = new Spark(4);
        elevatorMotor1.setInverted(false);
        elevatorMotor2 = new Spark(5);
        elevatorMotor2.setInverted(false);
        elevatorMotorGroup = new SpeedControllerGroup(elevatorMotor1, elevatorMotor2);

        intakeServo = new Servo(9);
        // TODO: Enable safety
        drivetrainLeftTalon.setSafetyEnabled(false);
        drivetrainRightTalon.setSafetyEnabled(false);
        drivetrainLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //drivetrainLeftTalon.configOpenloopRamp(0, 0);
        //drivetrainLeftTalon.configClosedloopRamp(2, 0);
        drivetrainRightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //drivetrainRightTalon.configOpenloopRamp(0, 0);
        //drivetrainRightTalon.configClosedloopRamp(2, 0);
        drivetrainRightTalon.setInverted(true);
        
        drivetrainLeftSlaveTalon = new WPI_TalonSRX(3);
        drivetrainLeftSlaveTalon.follow(drivetrainLeftTalon);
        drivetrainRightSlaveTalon = new WPI_TalonSRX(2);
        drivetrainRightSlaveTalon.follow(drivetrainRightTalon);
        drivetrainRightSlaveTalon.setInverted(true);
    }
    
	public static double limit(double value) {
		return Math.copySign(Math.min(Math.abs(value), 1.0), value);
	}
	
	public static double applyDeadband(double value, double deadband) {
		return Math.copySign(Math.max((Math.abs(value) - deadband) / (1.0 - deadband), 0.0), value);
	}
}
