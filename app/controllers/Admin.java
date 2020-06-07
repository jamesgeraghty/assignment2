package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.Trainer;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member trainer = Accounts.getLoggedInMember();
        List<Assessment> assessment = Assessment.findAll();
        List<Trainer> members = Trainer.findAll();
        render ("dashboard.html",members);
    }


    public static void deleteMember(Long id) {
        Member member = Member.findById(id);
        Logger.info("Removing" + member.name);
        member.delete();
        redirect("/dashboard");
    }

}