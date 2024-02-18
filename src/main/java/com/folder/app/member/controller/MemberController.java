package com.folder.app.member.controller;

import com.folder.app.member.dto.MemberDto;
import com.folder.app.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto) {

        /* *******************************************
         *  DTO Class와 index.html의 name="" 이 같다면 DTO에 @Setter 값을 자동으로 가져다줌
         *
         * @ModelAttribute 는 생략 가능하나 명시적으로 보일 수 있게 함
         *
        ********************************************* */

        System.out.println("MemberController.save");
        System.out.println(memberDto);
        memberService.save(memberDto);
//        MemberService memberService = new MemberService();  // 스프링부트에서는 이렇게 서비스로 안 보내도 보낼 수 있는 방법이 있다. @RequiredArgsConstructor
//        memberService.save();

        return "login";

    }

//    이것보다 쉬운 방법으로 코딩
//    public String save(@RequestParam("memberEmail") String memberEmail
//                  ,    @RequestParam("memberPassword") String memberPassword
//                  ,    @RequestParam("memberName") String memberName ) {
//        System.out.println("MemberController.save");
//        System.out.println(memberEmail + " / " + memberPassword + " / " + memberName);
//        return "index";
//    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null) {

            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();

        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDtoList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("member", memberDto);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId() ;
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
//        System.out.println(memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        // return checkResult -> Service에서 바로 값을 받아도 상관없음

        if (checkResult != null) {
            return "ok";
        } else {
            return "no";
        }
    }

}
