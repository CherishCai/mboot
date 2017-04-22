package cn.cherish.mboot.web.controller;

import cn.cherish.mboot.web.request.user.UserLoginReq;
import cn.cherish.mboot.common.shiro.CryptographyUtil;
import cn.cherish.mboot.common.shiro.ShiroUserUtil;
import cn.cherish.mboot.service.UserService;
import cn.cherish.mboot.util.QiniuUploadUtil;
import cn.cherish.mboot.util.ValidateCode;
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
import java.util.Map;

@Controller
public class BasicController extends ABaseController {

	@Autowired
	private UserService userService;

    /**
     * 首页
     */
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    /**
     * 管理页面
     */
    @GetMapping(value = "admin")
    public String admin(){
        return "admin/datapanel";
    }

	/**
	 * 登陆页面
	 */
	@GetMapping(value = "/login")
	public String login(){
		return "admin/login";
	}
	
	/**
	 * 执行登陆
	 */
	@PostMapping(value = "/login")
	public ModelAndView login(@Validated UserLoginReq loginVO, BindingResult bindingResult, HttpServletRequest request){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/login");
        Map<String, Object> errorMap = new HashMap<>();
        modelAndView.addObject("errorMap", errorMap);

		String code = (String) request.getSession().getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");
		//判断验证码
		if (StringUtils.isBlank(submitCode) || !StringUtils.equalsIgnoreCase(code,submitCode.toLowerCase())) {
			LOGGER.debug("验证码不正确");
            errorMap.put("validateCodeError", "验证码不正确");
            //添加上表单输入数据返回给页面
            modelAndView.addObject("usernameInput", loginVO.getUsername());
            modelAndView.addObject("passwordInput", loginVO.getPassword());
			return modelAndView;
		}

		//表单验证是否通过
		if (bindingResult.hasErrors()) {
			errorMap.putAll(getErrors(bindingResult));
            //添加上表单输入数据返回给页面
            modelAndView.addObject("usernameInput", loginVO.getUsername());
            modelAndView.addObject("passwordInput", loginVO.getPassword());

		}else {
			//实现登陆
			UsernamePasswordToken token = new UsernamePasswordToken(
					loginVO.getUsername(), CryptographyUtil.cherishSha1(loginVO.getPassword()));
			//token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();

			try {
				//subject.login(token);就会调用 ShiroRealm的 doGetAuthenticationInfo方法
				subject.login(token);

				Session session = subject.getSession();
				session.setAttribute("msg", "登陆成功");
				session.setAttribute("username", ShiroUserUtil.getUsername());
				session.setAttribute("nickname", ShiroUserUtil.getNickname());

			} catch (UnknownAccountException uae) {
				LOGGER.debug("账户不存在!");
				errorMap.put("username","账户或密码错误，请重新输入");
			} catch (IncorrectCredentialsException ice) {
				errorMap.put("username","账户或密码错误，请重新输入");
				LOGGER.debug("密码不正确!");
			} catch (LockedAccountException lae) {
				LOGGER.debug("账户被冻结!");
				errorMap.put("username","该账户被冻结");
			}catch(ExcessiveAttemptsException eae){
				LOGGER.debug("错误次数过多");
				errorMap.put("username","密码错误次数过多，请稍后再试");
			} catch (AuthenticationException ae) {
				token.clear();
				errorMap.put("username","系统认证错误");
				LOGGER.debug("认证错误!");
			}

			if (subject.isAuthenticated()){
				LOGGER.debug("登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
				modelAndView.setViewName("redirect:/admin");
			}
		}

		return modelAndView;
	}

	@GetMapping("/403")
	public String unauthorizedRole(){
		LOGGER.debug("------没有权限-------");
		return "error/403";
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

	/**
	 * 应该添加权限
	 * @return qiniuToken
	 */
	@GetMapping("qiniuToken")
	@ResponseBody
	public String qiniuToken(){
		String uptoken = QiniuUploadUtil.getUpToken();
		return "{\"uptoken\":\"" + uptoken + "\"}";
	}

	//文件存放路径
	private static final String FILE_PATH = "F:/cherish";

	@PostMapping("/imageUpload")
	@ResponseBody
	public String upload(@RequestParam("wangEditorH5File") MultipartFile multipartFile, HttpServletRequest request){
		String url = "";
		if (!multipartFile.isEmpty()) {
			File directory = new File(FILE_PATH);

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
				url = basePath + "imageDownload?filename=" + newFIleName;
			} catch (IOException e) {
				e.printStackTrace();
			}

		} // end if
		return url;
	}

	@GetMapping("/imageDownload")
	public ResponseEntity<byte[]> downloadImage(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
		File file = new File(FILE_PATH, filename);

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
