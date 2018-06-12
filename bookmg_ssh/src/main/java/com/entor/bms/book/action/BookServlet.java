package com.entor.bms.book.action;

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

import com.entor.bms.book.entity.BookInfo;
import com.entor.bms.book.service.BookService;
import com.entor.bms.book.service.impl.BookServiceImpl;
import com.entor.bms.user.entity.UserInfo;
import com.entor.bms.user.service.UserService;
import com.entor.bms.user.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger();

	private BookService bookService = new BookServiceImpl();
	private UserService userService = new UserServiceImpl();

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "D:\\JSD1801\\wubin\\server\\img\\books";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	// 需要使用到Gson
	private Gson gson = new Gson();
	private Type listType = new TypeToken<List<BookInfo>>() {
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
		case "undercarriage":
			undercarriage(request, response);
			break;
		case "grounding":
			grounding(request, response);
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
		case "upload":
			upload(request, response);
			break;
		case "getThumbnail":
			getThumbnail(request, response);
			break;
		case "search":
			search(request, response);
			break;
		case "rent":
			rent(request, response);
			break;
		case "return":
			returnBook(request, response);
			break;
		}
	}

	/**
	 * 根据请求封装书籍列表的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookInfo> list = bookService.getAllBooks();
		String listJson = "{\"total\":" + list.size() + ", \"rows\":" + gson.toJson(list, listType) + "}";
		LOGGER.info("list() -> listJson = {}", listJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(listJson);
		out.flush();
	}

	/**
	 * 根据请求封装书籍的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookInfo bookInfo = bookService.getBookByIdAndStatus(Integer.parseInt(request.getParameter("bid")), 1);
		String bookJson = gson.toJson(bookInfo, BookInfo.class);
		LOGGER.info("get() -> bookJson = {}", bookJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(bookJson);
		out.flush();
	}

	/**
	 * 根据请求封装搜索书籍列表的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("search() -> keywords = {}", request.getParameter("keywords"));
		List<BookInfo> list = bookService.searchBooksByName(request.getParameter("keywords"));
		String listJson = "{\"total\":" + list.size() + ", \"rows\":" + gson.toJson(list, listType) + "}";
		LOGGER.info("search() -> listJson = {}", listJson);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(listJson);
		out.flush();
	}

	/**
	 * 查看书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			request.setAttribute("BookInfo", bookService.queryByBookId(Integer.parseInt(request.getParameter("bid"))));
			// get方式请求，返回一个修改页面
			request.getRequestDispatcher("lookBook.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * 下架书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void undercarriage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("froze() invoked！");
		bookService.updateStatusById(Integer.parseInt(request.getParameter("bid")), 0);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 上架书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void grounding(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("grounding() invoked！");
		bookService.updateStatusById(Integer.parseInt(request.getParameter("bid")), 1);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 删除书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("delete() invoked！");
		bookService.removeUserById(Integer.parseInt(request.getParameter("bid")));
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("修改成功!");
		response.getWriter().flush();
	}

	/**
	 * 添加书籍
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
		BookInfo bookInfo = gson.fromJson(formData, BookInfo.class);

		LOGGER.info("add() ->　BookInfo ＝　{}", bookInfo);

		bookService.saveBook(bookInfo);

		// 刷新列表
		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"添加成功!\"}");
		out.flush();
	}

	/**
	 * 更新书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("get".equalsIgnoreCase(request.getMethod())) {
			request.setAttribute("BookInfo",
					bookService.getBookInfoById(Integer.parseInt(request.getParameter("bid"))));
			// get方式请求，返回一个修改页面
			request.getRequestDispatcher("updateBook.jsp").forward(request, response);
			return;
		}

		// 获取参数
		String formData = request.getParameter("formData");
		BookInfo info = gson.fromJson(formData, BookInfo.class);
		BookInfo bookInfo = bookService.getBookInfoById(info.getBid());
		bookInfo.setBookName(info.getBookName());
		bookInfo.setStatus(info.getStatus());
		LOGGER.info("update() ->　BookInfo ＝　{}", bookInfo);

		bookService.updateBookInfo(bookInfo);

		// 刷新列表
		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"修改成功!\"}");
		out.flush();
	}

	/**
	 * 获得个人相片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getThumbnail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("getThumbnail() invoked!");
		// 1.根据id获取个人信息
		BookInfo bookInfo = bookService.getBookInfoById(Integer.valueOf(request.getParameter("bid")));
		// 2.获得个人相片的磁盘路径
		String absolutePicPath = UPLOAD_DIRECTORY + File.separator + bookInfo.getThumbnail();
		// 3.获得相片的输入流
		FileInputStream fis = new FileInputStream(absolutePicPath);
		LOGGER.info("getThumbnail() -> fis = {}", fis);
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
						bookService.updateThumbnailById(Integer.valueOf(request.getParameter("bid")), fileName);
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
	 * 批量删除书籍
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void batchDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("batch() -> bids = {}", Arrays.toString(request.getParameterValues("bids")));

		bookService.batchDeleteByIds(request.getParameterValues("bids"));

		PrintWriter out = response.getWriter();
		out.write("{\"success\":true,\"msg\":\"批量删除成功!\"}");
		out.flush();
	}

	/**
	 * 借书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void rent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("rent() -> bid = {}", request.getParameter("bid"));
		LOGGER.info("rent() -> uid = {}", request.getParameter("uid"));

		PrintWriter out = response.getWriter();

		UserInfo userInfo = userService.getUserInfoById(Integer.parseInt(request.getParameter("uid")));
		if (userInfo == null) {
			out.write("{\"success\":false,\"msg\":\"借阅失败，借阅人ID不存在!\"}");
			out.flush();
			return;
		}

		bookService.rentBook(Integer.parseInt(request.getParameter("bid")), userInfo);

		out.write("{\"success\":true,\"msg\":\"借阅成功!\"}");
		out.flush();
	}

	/**
	 * 还书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void returnBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("returnBook() -> bid = {}", request.getParameter("bid"));
		LOGGER.info("returnBook() -> uid = {}", request.getParameter("uid"));

		PrintWriter out = response.getWriter();

		UserInfo userInfo = userService.getUserInfoById(Integer.parseInt(request.getParameter("uid")));
		if (userInfo.getRentBooks().indexOf(Integer.parseInt(request.getParameter("bid"))) == -1) {
			out.write("{\"success\":false,\"msg\":\"请输入该用户借阅书籍ID!\"}");
			out.flush();
			return;
		}

		BookInfo bookInfo = bookService.getBookByIdAndStatus(Integer.parseInt(request.getParameter("bid")), 1);
		if (bookInfo == null) {
			out.write("{\"success\":false,\"msg\":\"该书已下架，请管理员确认，进行人工还书!\"}");
			out.flush();
			return;
		}

		bookService.returnBook(Integer.parseInt(request.getParameter("bid")), userInfo);

		out.write("{\"success\":true,\"msg\":\"还书成功!\"}");
		out.flush();
	}
}
