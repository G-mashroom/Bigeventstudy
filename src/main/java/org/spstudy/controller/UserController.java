package org.spstudy.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.spstudy.pojo.Result;
import org.spstudy.pojo.User;
import org.spstudy.service.UserService;
import org.spstudy.utils.JwtUtil;
import org.spstudy.utils.Md5Util;
import org.spstudy.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);

        if (u != null) {
            //用户名被占用
            return Result.error("用户名已使用");
        }else{
            //没有被占用 注册
            userService.register(username,password);
            return Result.success();
        }
        //注册用户
    }
    @PostMapping("/login")

    public Result login(@Pattern(regexp = "^\\S{5,16}") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据对象查询用户
        User loginUser = userService.findByUserName(username);

        // 判断用户是否存在
        if (loginUser == null) {
            return Result.error("用户不存在");
        }

        // 验证密码
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");

    }

    @GetMapping("/userInfo")  //注意区分大小写
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        //根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String)map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();

        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);


    }

    @PutMapping("/update")
    public Result upadte(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();

    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码,再和old_pwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
//        //删除redis中对应的token
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        operations.getOperations().delete(token);
        return Result.success();
    }

}
