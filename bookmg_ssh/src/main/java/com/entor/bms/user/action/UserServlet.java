package com.entor.bms.user.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.entor.bms.user.entity.UserInfo;
import com.entor.bms.user.service.UserService;
import com.entor.bms.user.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger();

	private UserService userService = new UserServiceImpl();

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "D:\\JSD1801\\wubin\\server\\img";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	// 需要使用到Gson
	private Gson gson = new Gson();
	private Type listType = new TypeToken<List<UserInfo>>() {
	}.getType();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestSwitch(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestSwitch(request, response);
	}

	private void requestSwitch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("requestSwitch() -> tp = {}", request.getParameter("tp"));
		request.setCharacterEncoding("utf-8");
		// UserServlet只能在登录状况下允许get请求
		if (request.getMethod().equalsIgnoreCase("get") && request.getSession().getAttribute("user") == null) {
			response.sendRedirect("login.jsp?login_msg=please login!");
			return;
		}

		// 派发请求
		switch (request.getParameter("tp")) {
		case "list":
			list(request, response);
			break;
		case "get":
			get(request, response);
			break;
		case "add":
			add(request, response);
			break;
		case "look":
			look(request, response);
			break;
		case "froze":
			froze(request, response);
			break;
		case "unfroze":
			unfroze(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "batchDel":
			batchDel(request, response);
			break;
		case "isIdCardExists":
			isIdCardExists(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "upload":
			upload(request, response);
			break;
		case "pic":
			getPersonelPic(request, response);
			break;
		case "alterpass":
			alterPass(request, response);
			break;
		case "search":
			search(request, response);
			break;
		}
	}

	/**
	 * 根据请求封装用户列表的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserInfo> list = userService.getAllUsers();
		String listJson = "{\"total\":" + list.size() + ", \"rows\":" + gson.toJson(list, listType) + "}";
		LOGGER.info("list() -> listJson = {}", listJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(listJson);
		out.flush();
	}

	/**
	 * 根据请求封装用户的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo userInfo = userService.getUserInfoById(Integer.parseInt(request.getParameter("uid")));
		String userJson = gson.toJson(userInfo, UserInfo.class);
		LOGGER.info("get() -> userJson = {}", userJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(userJson);
		out.flush();
	}

	/**
	 * 根据请求封装搜索用户列表的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("search() -> keywords = {}", request.getParameter("keywords"));
		List<UserInfo> list = userService.searchUsersByName(request.getParameter("keywords"));
		String listJson = "{\"total\":" + list.size() + ", \"rows\":" + gson.toJson(list, listType) + "}";
		LOGGER.info("search() -> listJson = {}", listJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(listJson);
		out.flush();
	}

	/**
	 * 查看用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			request.setAttribute("userInfo",
					userService.getUserInfoById(Integer.parseInt(request.getParameter("uid"))));
			// get方式请求，返回一个修改页面
			request.getRequestDispatcher("lookUser.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * 冻结用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void froze(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("froze() invoked！");
		userService.updateStatusById(Integer.parseInt(request.getParameter("uid")), 0);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 解冻用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unfroze(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("unfroze() invoked！");
		userService.updateStatusById(Integer.parseInt(request.getParameter("uid")), 1);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("delete() invoked！");
		userService.removeUserById(Integer.parseInt(request.getParameter("uid")));
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			// get方式请求，返回一个修改页面
			request.setAttribute("login_msg", "请先登录!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		// 获取参数
		String formData = request.getParameter("formData");
		UserInfo userInfo = gson.fromJson(formData, UserInfo.class);
		userInfo.setPassword(userInfo.getIdCard());

		LOGGER.info("add() ->　userInfo ＝　{}", userInfo);

		userService.saveUser(userInfo);

		// 刷新列表
		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"添加成功!\"}");
		out.flush();
	}

	/**
	 * 更新用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			request.setAttribute("userInfo",
					userService.getUserInfoById(Integer.parseInt(request.getParameter("uid"))));
			// get方式请求，返回一个修改页面
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
			return;
		}

		// 获取参数
		String formData = request.getParameter("formData");
		UserInfo info = gson.fromJson(formData, UserInfo.class);
		UserInfo userInfo = userService.getUserInfoById(info.getUid());
		userInfo.setName(info.getName());
		userInfo.setPassword(info.getPassword());
		userInfo.setStatus(info.getStatus());
		LOGGER.info("update() ->　userInfo ＝　{}", userInfo);

		userService.updateUserInfoByIdCard(userInfo);

		// 刷新列表
		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"修改成功!\"}");
		out.flush();
	}

	/**
	 * 判断请求idCard是否存在
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void isIdCardExists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("isIdCardExists() invoked!");
		UserInfo userInfo = userService.getUserInfoByIdCard(request.getParameter("idCard"));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (userInfo == null) {// 不存在
			out.write("{\"msg\":false}");
			out.flush();
			return;
		}

		// 存在
		out.write("{\"msg\":true}");
		out.flush();
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("logout() invoked!");
		// 清空user
		request.getSession().setAttribute("user", null);
		response.sendRedirect("login.jsp");
	}

	/**
	 * 获得个人相片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getPersonelPic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("getPersonelPic() invoked!");
		// 1.根据id获取个人信息
		UserInfo userInfo = userService.getUserInfoById(Integer.valueOf(request.getParameter("uid")));
		// 2.获得个人相片的磁盘路径
		String absolutePicPath = UPLOAD_DIRECTORY + File.separator + userInfo.getPic();
		// 3.获得相片的输入流
		FileInputStream fis = new FileInputStream(absolutePicPath);
		LOGGER.info("getPersonelPic() -> fis = {}", fis);
		// 4.将输入流写到响应输出流中
		OutputStream os = response.getOutputStream();
		byte[] buff = new byte[100];
		int length;
		while ((length = fis.read(buff)) != -1) {
			os.write(buff, 0, length);
		}
		os.flush();
	}

	/**
	 * 获得个人相片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void alterPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("get".equalsIgnoreCase(request.getMethod())) {
			request.getSession().setAttribute("login_mgs", "请先登录！");
			response.sendRedirect("login.jsp");
			return;
		}
		String formData = request.getParameter("formData");
		LOGGER.info("doPost() -> formData = {}", formData);
		String[] params = formData.replace("{", "").replace("}", "").split(",");
		String idCard = null, oldpass = null, newpass = null;
		for (String field : params) {
			String[] keyAndValue = field.split(":");
			if ("idCard".equals(keyAndValue[0].replaceAll("\"", ""))) {
				idCard = keyAndValue[1].replaceAll("\"", "");
			} else if ("oldpass".equals(keyAndValue[0].replaceAll("\"", ""))) {
				oldpass = keyAndValue[1].replaceAll("\"", "");
			} else if ("newpass".equals(keyAndValue[0].replaceAll("\"", ""))) {
				newpass = keyAndValue[1].replaceAll("\"", "");
			}
		}
		LOGGER.info("doPost() -> idCard = {},oldpass = {},newpass = {}", idCard, oldpass, newpass);
		// 验证旧密码是否正确
		PrintWriter out = response.getWriter();
		UserInfo userInfo = userService.getUserInfoByIdCard(idCard);
		LOGGER.info("doPost() -> userInfo = {}", userInfo);
		if (userInfo == null || !oldpass.equals(userInfo.getPassword())) {
			out.write("{\"success\":false,\"msg\":\"旧密码不正确!\"}");
			out.flush();
			return;
		}

		// 旧密码验证通过，更新新密码
		userService.alterPassByIdCard(idCard, newpass);
		out.write("{\"success\":true,\"msg\":\"修改成功!\"}");
		out.flush();
	}

	/**
	 * 上传照片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("logout() upload!");
		PrintWriter writer = response.getWriter();
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			writer.println("Error: 表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// 上传对象
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 如果目录不存在则创建
		File uploadDir = new File(UPLOAD_DIRECTORY);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		// 解析请求的内容提取文件数据
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String origionName = new File(item.getName()).getName();
						String fileName = System.currentTimeMillis() + origionName.substring(origionName.indexOf("."));
						String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
						// 图片文件最终的路径（包含了文件名）
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						// 将图片名称更新到数据库
						userService.updatePicById(Integer.valueOf(request.getParameter("uid")), fileName);
					}
				}
			}
		} catch (Exception ex) {
			writer.write("{\"msg\":\"上传失败！\"}");
			writer.flush();
		}
		writer.write("{\"msg\":\"上传成功！\"}");
		writer.flush();
	}

	/**
	 * 批量删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void batchDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("batch() -> uids = {}", Arrays.toString(request.getParameterValues("uids")));

		userService.batchDel(request.getParameterValues("uids"));

		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"批量删除成功!\"}");
		out.flush();
	}
}
