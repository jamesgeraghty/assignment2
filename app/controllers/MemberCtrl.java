package controllers;

import models.Member;
import models.Assessment;
import models.Trainer;
import play.mvc.Controller;
import play.Logger;

import java.util.List;

public class MemberCtrl extends Controller
{
    public static void index(Long id)
{
    Member member = Member.findById(id);
    Logger.info ("Member id = " + id);
    render("member.html",member);

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
        Member member = Member.findById(id);
        member.assessments.add(assessment);
        member.save();
        redirect ("/member/" + id);
    }

    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        Logger.info ("Removing" + member.name);
        member.delete();
        Trainer trainer = Accounts.getLoggedInTrainer();
        trainer.save();
        redirect("/trainerdashboard");
    }


    public static void addComment(Long id, Long assessmentid, String comment) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.setComment(comment);
        assessment.save();
        Logger.info("Adding a Comment" + comment);
        render("member.html", member);
    }
}