package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Member;
import models.Trainer;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

/**
 *
 */
public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    member.getBMI();
    member.bmiAnalytics();
    //member.isIdealBodyWeight();
    List<Assessment> assessments = member.assessments;
    render("dashboard.html", member,assessments);
  }

  public static void indexTrainer() {
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members= Member.findAll();
    for (Member member : members) {
      member.getBMI();
      member.bmiAnalytics();
    }
    render("trainerDashboard.html",trainer,members);
  }

  public static void deleteassessment (Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    Logger.info("Removing" + assessment.weight + assessment.chest + assessment.thigh
            + assessment.upperArm + assessment.waist + assessment.hips);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();

    render("member.html", member);
  }
  public static void addAssessments(Long id, double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment) {
    Assessment assessment = new Assessment(weight, chest, thigh,upperArm,waist,hips,comment);
    Member member = Accounts.getLoggedInMember();
    member.assessments.add(assessment);
    member.save();
    redirect ("/dashboard" );
  }


  public static void addComment(String comment, Long id, Long assessmentid)
  {
    Logger.info("Adding a Comment");
    Assessment assessment = Assessment.findById(id);
    assessment.comment = comment;
    assessment.save();
    Trainer trainer = Accounts.getLoggedInTrainer();
    Member member = Member.findById(id);
    List<Assessment> assessmentlist = member.assessments;
    render("trainermemberdashboard.html", trainer, member, assessmentlist);
  }

}
