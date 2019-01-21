package frc.team691.robot2018;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team691.robot2018.commands.*;
import frc.team691.robot2018.subsystems.*;

public class Robot extends TimedRobot {
    UsbCamera camera;
    Command autonomousCommand;
    SendableChooser<Command> autoChooser;
    SendableChooser<Integer> spotChooser;
    AutoGroupTest agt;
    MotionMagicTest mmt;
    AutoMidGroup amg;

    public static OI oi;
    public static StickDrive sdrive;
    public static Drivetrain drivetrain;
    public static Intake intake;
    public static Winch winch;
    public static Elevator elevator;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        camera = CameraServer.getInstance().startAutomaticCapture();
        RobotMap.init();
        drivetrain = new Drivetrain();
        intake = new Intake();
        winch = new Winch();
        elevator = new Elevator();
        
        oi = new OI();
        sdrive = new StickDrive();
        
        spotChooser = new SendableChooser<>();
        spotChooser.setDefaultOption("Left", 1);
        spotChooser.addOption("Middle", 0);
        spotChooser.addOption("Right", -1);
        
        agt = new AutoGroupTest();
        mmt = new MotionMagicTest(240);
        amg = new AutoMidGroup();
        autoChooser = new SendableChooser<>();
        autoChooser.setDefaultOption("AutoGroupTest", agt);
        autoChooser.addOption("AutoMidGroup", amg);
        autoChooser.addOption("MotionMagicTest", mmt);

        SmartDashboard.putData("SpotChooser", spotChooser);
        SmartDashboard.putData("AutoChooser", autoChooser);
        SmartDashboard.putNumber("MatchNumber", SmartDashboard.getNumber("MatchNumber", 1));
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        int color, spot;
    	String gameMsg = DriverStation.getInstance().getGameSpecificMessage();
    	if (gameMsg == null || gameMsg.length() < 1) {
    		color = 1 - (2 * (((int) SmartDashboard.getNumber("MatchNumber", 1)) % 2));
        } else {
            color = (gameMsg.charAt(0) == 'L' ? 1 : -1);
        }
    	spot = spotChooser.getSelected();
    	System.out.println("My color on scale is " + color);
    	System.out.println("My spot on wall is " + spot);
    	agt.setColorSpot(color, spot);
    	amg.setColorSpot(color, spot);
    	elevator.servoSetRelease();
        autonomousCommand = autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
