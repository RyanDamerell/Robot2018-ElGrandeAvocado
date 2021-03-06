package com.team3925.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team3925.frc2018.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private final TalonSRX leftMaster = RobotMap.DrivetrainMap.LEFT_MASTER;

	private final TalonSRX rightMaster = RobotMap.DrivetrainMap.RIGHT_MASTER;

	private final DoubleSolenoid shiftSolenoid = RobotMap.DrivetrainMap.SHIFT_SOLENOID;

	public static Drivetrain instance;
	private static boolean shiftState = true;

	public static Drivetrain getInstance() {
		if (instance == null)
			instance = new Drivetrain();
		return instance;
	}

	private Drivetrain() {
		RobotMap.DrivetrainMap.LEFT_MASTER.setInverted(true);
		RobotMap.DrivetrainMap.LEFT_SLAVE_A.setInverted(true);
		RobotMap.DrivetrainMap.LEFT_SLAVE_B.setInverted(true);
		
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}

	public void setRaw(double l, double r) {
		leftMaster.set(ControlMode.PercentOutput, l);
		rightMaster.set(ControlMode.PercentOutput, r);
	}

	public void setShifter(boolean isHigh) {
		shiftSolenoid.set((isHigh) ? Value.kForward : Value.kReverse);
		shiftState = isHigh;
	}
	
	public boolean getShiftState() {
		return shiftState;
	}

	@Deprecated
	public TalonSRX getLeftMaster() {
		return leftMaster;
	}

	@Deprecated
	public TalonSRX getRightMaster() {
		return rightMaster;
	}

	@Override
	protected void initDefaultCommand() {
	}

}
