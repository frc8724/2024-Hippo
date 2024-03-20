// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.DriveBase;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class DriveForDistance extends Command {
  double m_startingSpeed;
  double m_finalSpeed;
  double m_direction;
  double m_orientation;
  double m_distance;

  public DriveForDistance(double startSpeed, double finalSpeed, double direction, double orientation,
      double distance) {
    m_startingSpeed = startSpeed;
    m_finalSpeed = finalSpeed;
    m_orientation = orientation;
    m_direction = direction;
    m_distance = distance;
    addRequirements(RobotContainer.m_robotDrive);
  }

  public DriveForDistance(double speed, double direction, double orientation, double distance) {
    this(speed, speed, direction, orientation, distance);
  }

  // Called when the command is initially scheduled.

  Pose2d pose = new Pose2d();

  @Override
  public void initialize() {
    this.pose = RobotContainer.m_robotDrive.getPose();
    RobotContainer.m_robotDrive.driveOnHeading(m_startingSpeed, m_direction, m_orientation);
  }

  // Called every time the scheduler runs while the command is scheduled.

  public void execute() {
    Pose2d p = RobotContainer.m_robotDrive.getPose();
    double dist = getDistance(p.getX() - this.pose.getX(), p.getY() - this.pose.getY());

    double power = m_startingSpeed + dist / m_distance * (this.m_finalSpeed - m_startingSpeed);

    RobotContainer.m_robotDrive.driveOnHeading(power, m_direction, m_orientation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  double getDistance(double x, double y) {
    return Math.sqrt(x * x + y * y);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Pose2d p = RobotContainer.m_robotDrive.getPose();
    double dist = getDistance(p.getX() - this.pose.getX(), p.getY() - this.pose.getY());
    // System.out.println("Distance =" + dist);
    return dist >= m_distance;
  }
}
