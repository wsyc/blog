package com.yanchong.blog.Controller;

import com.yanchong.blog.Entity.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class LoginController {

    private final Logger logger = Logger.getLogger("1");

    @PostMapping("/login")
    public String login(Model model, @Valid UserVo userVo, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("error",bindingResult.getFieldError().getDefaultMessage());
            return "/adm/pramsFail";
        }
        String md5 = new Md5Hash(userVo.getPassword()).toHex();
        String userName = userVo.getName();
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getName(), userVo.getPassword());
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，错误的凭证！");
            redirectAttributes.addFlashAttribute("error","用户名或密码不正确！");
        }catch(UnknownAccountException uae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，未知账户！");
            redirectAttributes.addFlashAttribute("error","未知账户！");
        }catch(LockedAccountException lae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，账户锁定！");
            redirectAttributes.addFlashAttribute("error","账户已锁定！");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，错误次数太多！");
            redirectAttributes.addFlashAttribute("error","用户名或密码错误次数太多！");
        }catch(AuthenticationException ae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，堆栈轨迹如下：！");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("error","用户名或密码不正确！");
        }

        if(currentUser.isAuthenticated()){
            model.addAttribute("name",userName);
//            return "index";
            return "redirect:/adm/index";
        }else{
            token.clear();
            return "redirect:/adm/fail";
        }
    }


    @GetMapping("/login")
    public String login(){
        return "/admin/login1";
    }
}
