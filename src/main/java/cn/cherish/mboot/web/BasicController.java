package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.extra.shiro.CryptographyUtil;
import cn.cherish.mboot.service.UserService;
import cn.cherish.mboot.util.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class BasicController {

	@Autowired
	private UserService userService;

    /**
     * 首页
     */
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "/index";
    }

    /**
     * 管理页面
     */
    @GetMapping(value = "admin")
    public String admin(){
        return "/admin/datapanel";
    }

	/**
	 * 登陆页面
	 */
	@GetMapping(value = "/login")
	public String login(){
		return "/admin/login";
	}
	
	/**
	 * 执行登陆
	 */
	@PostMapping(value = "/login")
	public ModelAndView login(@Validated User user, BindingResult msgResult, HttpServletRequest request){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usernameBefore", user.getUsername());
		modelAndView.addObject("passwordBefore", user.getPassword());
		modelAndView.setViewName("/admin/login");

		String code = (String) request.getSession().getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");
		//判断验证码
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
			log.debug("验证码不正确");
			modelAndView.addObject("validateCodeError", "验证码不正确");
			return modelAndView;
		}

		//表单验证是否通过
		if (msgResult.hasErrors()) {
			List<FieldError> list = msgResult.getFieldErrors();
			for (FieldError error : list) {
				modelAndView.addObject(error.getField(), error.getDefaultMessage());
			}
			return modelAndView;
		}

		//实现登陆
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), CryptographyUtil.cherishSha1(user.getPassword()));
		//token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			//subject.login(token);就会调用 ShiroRealm的 doGetAuthenticationInfo方法
			subject.login(token);

			Session session = subject.getSession();
			session.setAttribute("msg", "登陆成功");
			session.setAttribute("username", user.getUsername());

		} catch (UnknownAccountException uae) {
			log.debug("账户不存在!");
        } catch (IncorrectCredentialsException ice) {
			log.debug("密码不正确!");
        } catch (LockedAccountException lae) {
			log.debug("账户被禁了!");
		}catch(ExcessiveAttemptsException eae){
			log.debug("错误次数过多");
        } catch (AuthenticationException ae) {
        	token.clear();
			log.debug("认证错误!");
		}

		if (subject.isAuthenticated()){
			log.debug("登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			modelAndView.setViewName("redirect:/admin");
		}else{
			token.clear();
			modelAndView.setViewName("redirect:/admin/login");
		}

		return modelAndView;
	}

	@GetMapping("/403")
	public String unauthorizedRole(){
		log.debug("------没有权限-------");
		return "403";
	}
	
	/**
	 * 生成验证码
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 图片生成错误
	 */
	@GetMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

	@PostMapping("/imageUpload")
	@ResponseBody
	public Map upload(@RequestParam("editormd-image-file") MultipartFile multipartFile, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>(5);
		map.put("success", 0);//0 | 1, // 0 表示上传失败，1 表示上传成功
		map.put("message", "提示的信息，上传成功或上传失败及错误信息等。");

		if (!multipartFile.isEmpty()) {
			File directory = new File("/cherish");

			if (!directory.exists()) {
				directory.mkdirs();
			}

			try {
				String originalFilename = multipartFile.getOriginalFilename();

				String newFIleName = System.currentTimeMillis()//UUID.randomUUID().toString()
						+ originalFilename.substring(originalFilename.lastIndexOf("."));
				multipartFile.transferTo(new File(directory, newFIleName));
//				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),
//				new File(directory,newFIleName));
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/";
				map.put("success", 1);
				map.put("message", "掂过碌蔗！");
				map.put("url", basePath+"imageDownload?filename="+newFIleName);
			} catch (IOException e) {
				e.printStackTrace();
				map.put("success", 0);
				map.put("message", "错误!");
			}

		} // end if
		return map;
	}

	@GetMapping("/imageDownload")
	public ResponseEntity<byte[]> downloadImage(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
		File file = new File("/cherish", filename);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(
				FileUtils.readFileToByteArray(file),
				headers, HttpStatus.OK);
        /*byte[] b = null;
        response.setContentType("image/png");

        try (FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();){

            b = new byte[2048];
            int len = 0;
            while ((len = fis.read(b)) > 0){
                out.write(b);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }*/
	}
	
}